package com.example.kotlin_shop.ui.fragments.catalog

import android.content.res.Configuration
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.kotlin_shop.R
import com.example.kotlin_shop.di.components.DaggerAppComponent
import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.presentation.CatalogPresenter
import com.example.kotlin_shop.ui.MainActivity
import com.example.kotlin_shop.ui.fragments.BadInternetFragment
import com.example.kotlin_shop.ui.interfaces.CatalogView
import com.example.kotlin_shop.ui.recycler.CatalogAdapter
import com.example.kotlin_shop.ui.recycler.HintAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class CatalogFragment : MvpAppCompatFragment(R.layout.fragment_catalog), CatalogView {

    private val catalogAdapter = CatalogAdapter()

    private val hintAdapter = HintAdapter(::onSelect)

    private var isRecyclerShowed = true

    private var searchQuery: String = ""

    private lateinit var refresher: SwipeRefreshLayout

    private lateinit var searchBar: SearchView

    private lateinit var recyclerHints: RecyclerView

    @Inject
    lateinit var presenterProvider: Provider<CatalogPresenter>

    private val presenter by moxyPresenter { presenterProvider.get() }

    init {
        DaggerAppComponent.create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        refresher = view.findViewById(R.id.srlCatalogRefresher)
        searchBar = view.findViewById(R.id.svSearchProducts)

        refresher.setOnRefreshListener {
            refresher.isRefreshing = true
            presenter.getProducts(searchQuery)
        }

        refresher.isRefreshing = true

        showRecycler()

        presenter.getProducts()

        view.findViewById<Button>(R.id.btnCatalogAddItem).setOnClickListener {
            presenter.addItem()
        }

        recyclerHints = view.findViewById<RecyclerView>(R.id.rvProductSearchHints)

        recyclerHints.apply {
            setHasFixedSize(true)

            layoutManager = LinearLayoutManager(context)

            adapter = hintAdapter
        }

        recyclerHints.visibility = View.INVISIBLE

        searchBar.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchBar.clearFocus()
                if(query!= null)
                    presenter.getProducts(searchQuery)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText != null && newText != "")
                    presenter.getHints(newText)
                searchQuery = newText ?: ""
                return true
            }
        })

        searchBar.setOnQueryTextFocusChangeListener { _, hasFocus ->
            recyclerHints.visibility = if(hasFocus) View.VISIBLE else View.INVISIBLE
        }
    }

    private fun onSelect(title: String){
        searchBar.setQuery(title as CharSequence, false)
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
        hintAdapter.loadData(hints)
    }
}