package com.example.kotlin_shop.ui.fragments.catalog

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.kotlin_shop.R
import com.example.kotlin_shop.di.components.DaggerAppComponent
import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.presentation.CatalogPresenter
import com.example.kotlin_shop.ui.fragments.BadInternetFragment
import com.example.kotlin_shop.ui.fragments.catalog.CatalogRecyclerFragment
import com.example.kotlin_shop.ui.interfaces.CatalogView
import com.example.kotlin_shop.ui.recycler.CatalogAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class CatalogFragment : MvpAppCompatFragment(R.layout.fragment_catalog), CatalogView {

    private val catalogAdapter = CatalogAdapter()

    private lateinit var refresher: SwipeRefreshLayout

    @Inject
    lateinit var presenterProvider: Provider<CatalogPresenter>

    private val presenter by moxyPresenter { presenterProvider.get() }

    init {
        DaggerAppComponent.create().inject(this)
    }

    private var isRecyclerShowed = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        refresher = view.findViewById(R.id.srlCatalogRefresher)

        refresher.setOnRefreshListener {
            refresher.isRefreshing = true
            presenter.getProducts()
        }

        refresher.isRefreshing = true

        showRecycler()

        presenter.getProducts()

        view.findViewById<Button>(R.id.btnCatalogAddItem).setOnClickListener {
            presenter.addItem()
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
}