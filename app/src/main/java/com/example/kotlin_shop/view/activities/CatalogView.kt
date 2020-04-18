package com.example.kotlin_shop.view.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_shop.R
import com.example.kotlin_shop.model.Product
import com.example.kotlin_shop.presenter.CatalogPresenter
import com.example.kotlin_shop.view.ICatalogView
import com.example.kotlin_shop.view.recycler.CatalogAdapter
import kotlinx.android.synthetic.main.catalog_layout.*

class CatalogView : AppCompatActivity(), ICatalogView {

    private val recyclerAdapter: CatalogAdapter = CatalogAdapter()
    private val viewManager = LinearLayoutManager(this)
    private val presenter = CatalogPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.catalog_layout)

        presenter.attachView(this)

        recyclerView.apply {
            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = recyclerAdapter
        }

        btnToCart.setOnClickListener {
            val intent = Intent(this, CartView::class.java)

            startActivity(intent)
        }

        presenter.showProducts()
    }

    override fun showProducts(products: List<Product>) {
        recyclerAdapter.changeItems(products)
    }
}