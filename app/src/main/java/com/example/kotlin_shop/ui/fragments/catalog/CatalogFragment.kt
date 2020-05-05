package com.example.kotlin_shop.ui.fragments.catalog

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.kotlin_shop.R
import com.example.kotlin_shop.di.components.DaggerAppComponent
import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.presentation.CatalogPresenter
import com.example.kotlin_shop.ui.HintsAdapter
import com.example.kotlin_shop.ui.fragments.BadInternetFragment
import com.example.kotlin_shop.ui.interfaces.CatalogView
import com.example.kotlin_shop.ui.recycler.CatalogAdapter
import com.mancj.materialsearchbar.MaterialSearchBar
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider


class CatalogFragment : MvpAppCompatFragment(R.layout.fragment_catalog), CatalogView {

    private val catalogAdapter = CatalogAdapter()

    private var isRecyclerShowed = true

    private var searchQuery: String = ""

    private var wasShown: Boolean = false

    private lateinit var refresher: SwipeRefreshLayout

    private lateinit var searchBar: MaterialSearchBar

    private lateinit var hintAdapter: HintsAdapter

    @Inject
    lateinit var presenterProvider: Provider<CatalogPresenter>

    private val presenter by moxyPresenter { presenterProvider.get() }

    init {
        DaggerAppComponent.create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        hintAdapter = HintsAdapter(requireContext(),::onSelect)

        refresher = view.findViewById(R.id.srlCatalogRefresher)
        searchBar = view.findViewById(R.id.svSearchProducts)

        refresher.setOnRefreshListener {
            refresher.isRefreshing = true
            presenter.getProducts(searchQuery)
        }

        refresher.isRefreshing = true

        showRecycler()

        view.findViewById<Button>(R.id.btnCatalogAddItem).setOnClickListener {
            presenter.addItem()
        }

        searchBar.setHint("Найти...")
        searchBar.setSpeechMode(true)
        searchBar.setOnSearchActionListener(object: MaterialSearchBar.OnSearchActionListener{
            override fun onButtonClicked(buttonCode: Int) {
            }

            override fun onSearchStateChanged(enabled: Boolean) {
                if(!enabled)
                    presenter.getProducts("")
            }

            override fun onSearchConfirmed(text: CharSequence?) {
                refresher.isRefreshing = true
                presenter.getProducts(text?.toString() ?: "")
            }
        })

        searchBar.setCustomSuggestionAdapter(hintAdapter)
        hintAdapter.suggestions = listOf("", "", "")

        searchBar.setCardViewElevation(3)

        searchBar.addTextChangeListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s != null && s != ""){
                    searchQuery = s.toString()
                    presenter.getHints(s.toString())
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
        if(!wasShown){
            wasShown = true
            presenter.getProducts("")
        }
    }

    private fun onSelect(title: String){
        searchBar.text = title
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
        refresher.isRefreshing = false
        catalogAdapter.changeItemSource(products)
    }

    override fun onAddCatalogItem(product: Product) {
        catalogAdapter.addItem(product)
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun showNetworkError() {
        refresher.isRefreshing = false

        childFragmentManager
            .beginTransaction()
            .replace(R.id.flCatalogFragment,
                BadInternetFragment()
            )
            .commit()
        isRecyclerShowed = false

    }

    override fun showHints(hints: List<String>) {
        hintAdapter.suggestions = hints
        hintAdapter.notifyDataSetChanged()
    }
}