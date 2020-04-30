package com.example.kotlin_shop.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.kotlin_shop.R
import com.example.kotlin_shop.di.components.DaggerAppComponent
import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.presentation.CatalogPresenter
import com.example.kotlin_shop.ui.interfaces.CatalogView
import com.example.kotlin_shop.ui.recycler.CatalogAdapter
import com.example.kotlin_shop.ui.recycler.ViewedProductsAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class CatalogFragment : MvpAppCompatFragment(R.layout.fragment_catalog), CatalogView {

    private val catalogAdapter = CatalogAdapter()

    private val viewedAdapter = ViewedProductsAdapter()

    private lateinit var refresher: SwipeRefreshLayout

    @Inject
    lateinit var presenterProvider: Provider<CatalogPresenter>

    private val presenter by moxyPresenter { presenterProvider.get() }

    init {
        DaggerAppComponent.create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        refresher = view.findViewById(R.id.srlCatalogRefresher)

        refresher.setOnRefreshListener {
            refresher.isRefreshing = true
            presenter.getProducts()
        }

        refresher.isRefreshing = true

        val catalogRecycler = view.findViewById<RecyclerView>(R.id.rvCatalog)

        val catalogManager = GridLayoutManager(context, 2)

        catalogRecycler.apply {
            setHasFixedSize(true)

            layoutManager = catalogManager

            adapter = catalogAdapter
        }

        presenter.getProducts()

        val viewedRecycler = view.findViewById<RecyclerView>(R.id.rvViewedItems)

        val viewedManager = LinearLayoutManager(context)

        viewedManager.orientation = LinearLayoutManager.HORIZONTAL

        viewedRecycler.apply{
            setHasFixedSize(true)

            layoutManager = viewedManager

            adapter = viewedAdapter
        }

        presenter.getViewed()

        view.findViewById<Button>(R.id.btnCatalogAddItem).setOnClickListener {
            presenter.addItem()
        }
    }

    override fun onResume() {
        super.onResume()

        presenter.getViewed()
    }

    override fun showProducts(products: MutableList<Product>) {
        refresher.isRefreshing = false
        catalogAdapter.changeItemSource(products)
    }



    override fun onAddCatalogItem() {
        catalogAdapter.notifyItemAdded()
    }

    override fun showViewed(products: MutableList<Product>) {
        viewedAdapter.changeItemSource(products)
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}