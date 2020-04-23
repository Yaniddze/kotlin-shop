package com.example.kotlin_shop.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_shop.R
import com.example.domain.Product
import com.example.kotlin_shop.presenter.CatalogPresenter
import com.example.kotlin_shop.view.interfaces.ICatalogView
import com.example.kotlin_shop.view.recycler.CatalogAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class CatalogFragment : MvpAppCompatFragment(R.layout.fragment_catalog), ICatalogView {

    private val recyclerAdapter = CatalogAdapter()

    private val presenter by moxyPresenter { CatalogPresenter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.rvCatalog)

        val viewManager = LinearLayoutManager(recycler.context)

        recycler.apply {
            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = recyclerAdapter
        }

        presenter.getProducts()

        view.findViewById<Button>(R.id.btnCatalogAddItem).setOnClickListener {
            presenter.addItem()
        }
    }

    override fun showProducts(products: MutableList<Product>) {
        recyclerAdapter.changeItemSource(products)
    }

    override fun onAddItem(product: Product) {
        recyclerAdapter.addItem(product)
    }
}
