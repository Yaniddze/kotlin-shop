package com.example.kotlin_shop.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_shop.R
import com.example.kotlin_shop.model.Product
import com.example.kotlin_shop.presenter.CatalogPresenter
import com.example.kotlin_shop.view.ICatalogView
import com.example.kotlin_shop.view.recycler.CatalogAdapter

class CatalogFragment : Fragment(), ICatalogView {

    private val recyclerAdapter = CatalogAdapter()

    private val presenter = CatalogPresenter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_catalog, container, false)

        presenter.attachView(this)

        val recycler = root.findViewById<RecyclerView>(R.id.rvCatalog)

        val viewManager = LinearLayoutManager(recycler.context)

        recycler.apply {
            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = recyclerAdapter
        }

        presenter.getProducts()

        return root
    }

    override fun showProducts(products: List<Product>) {
        recyclerAdapter.changeItemSource(products)
    }
}
