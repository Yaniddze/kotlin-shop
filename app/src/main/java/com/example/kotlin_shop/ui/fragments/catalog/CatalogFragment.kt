package com.example.kotlin_shop.ui.fragments.catalog

import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.kotlin_shop.App
import com.example.kotlin_shop.R
import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.presentation.CatalogPresenter
import com.example.kotlin_shop.ui.fragments.BadInternetFragment
import com.example.kotlin_shop.ui.interfaces.CatalogView
import com.example.kotlin_shop.ui.adapters.CatalogAdapter
import kotlinx.android.synthetic.main.fragment_catalog.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class CatalogFragment : MvpAppCompatFragment(R.layout.fragment_catalog), CatalogView {

    private val catalogAdapter = CatalogAdapter(::onFavoriteClick)

    private var isRecyclerShowed = true
    private var isSearchShowed = false
    private var wasFragmentShown: Boolean = false
    private var searchQuery: String = ""
    private lateinit var suggestionAdapter: ArrayAdapter<String>

    @Inject
    lateinit var presenterProvider: Provider<CatalogPresenter>
    private val presenter by moxyPresenter { presenterProvider.get() }
    init {
        App.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        suggestionAdapter = ArrayAdapter<String>(
            requireContext(), android.R.layout.simple_expandable_list_item_1)

        srlCatalogRefresher.setOnRefreshListener {
            srlCatalogRefresher.isRefreshing = true
            presenter.getProducts(searchQuery)
        }

        srlCatalogRefresher.isRefreshing = true

        showRecycler()
        if(isSearchShowed){
            showSearchBar()
        } else {
            hideSearchBar()
        }

        view.findViewById<Button>(R.id.btnCatalogAddItem).setOnClickListener {
            presenter.addItem()
        }
    }

    override fun onStart() {
        super.onStart()
        if(!wasFragmentShown){
            wasFragmentShown = true
            presenter.getProducts("")
        }
        view?.clearFocus()

        presenter.refreshFavorites(catalogAdapter.dataSet.toMutableList())
    }

    private fun onSearchBtn(query: String){
        searchQuery = query
        srlCatalogRefresher.isRefreshing = true
        presenter.getProducts(query)
    }

    private fun onDisableSearch(){
        hideSearchBar()
        searchQuery = ""
        srlCatalogRefresher.isRefreshing = true
        presenter.getProducts(searchQuery)
    }

    private fun hideSearchBar() {
        childFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.hide_from_top,
                R.anim.show_from_bottom,
                R.anim.show_from_bottom,
                R.anim.hide_from_top
            )
            .replace(flCatalogSearch.id, DisabledSearchLayout(::showSearchBar))
            .commit()

        isSearchShowed = false
    }

    private fun showSearchBar() {
        childFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.hide_from_top,
                R.anim.show_from_bottom,
                R.anim.show_from_bottom,
                R.anim.hide_from_top
            )
            .replace(flCatalogSearch.id,
                ActiveSearchFragment(
                    ::onDisableSearch,
                    searchQuery,
                    ::saveSearchQuery,
                    ::onSearchBtn,
                    presenter::getHints,
                    suggestionAdapter
                )
            )
            .commit()

        isSearchShowed = true
    }

    private fun saveSearchQuery(query: String){
        searchQuery = query
    }

    private fun onFavoriteClick(product: Product){
        if(!product.isFavorite){
            presenter.addToFavorite(product)
        }
        else{
            presenter.deleteFromFavorite(product)
        }
    }

    private fun showRecycler(){
        childFragmentManager
            .beginTransaction()
            .replace(R.id.flCatalogFragment,
                CatalogRecyclerFragment(
                    catalogAdapter
                )
            )
            .commit()

        isRecyclerShowed = true
    }

    override fun showProducts(products: MutableList<Product>) {
        if(!isRecyclerShowed){
            showRecycler()
        }
        srlCatalogRefresher.isRefreshing = false

        catalogAdapter.changeItemSource(products)
    }

    override fun onAddCatalogItem(product: Product) {
        catalogAdapter.addItem(product)
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun showNetworkError() {
        srlCatalogRefresher.isRefreshing = false

        childFragmentManager
            .beginTransaction()
            .replace(R.id.flCatalogFragment,
                BadInternetFragment()
            )
            .commit()

        isRecyclerShowed = false
    }

    override fun showHints(hints: List<String>) {
        suggestionAdapter.clear()
        suggestionAdapter.addAll(hints)
    }

    override fun onFavoriteAdded(product: Product) {
        catalogAdapter.notifyProductChanged(product)
    }

    override fun onFavoriteDeleted(product: Product) {
        catalogAdapter.notifyProductChanged(product)
    }

    override fun onFavoriteRefreshed(changedIds: List<Int>) {
        changedIds.forEach { id ->
            val founded = catalogAdapter.dataSet.first{ it.id == id.toString()}
            catalogAdapter.notifyProductChanged(founded)
        }
    }
}