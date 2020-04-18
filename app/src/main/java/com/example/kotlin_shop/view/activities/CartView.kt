package com.example.kotlin_shop.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_shop.R
import com.example.kotlin_shop.model.Product
import com.example.kotlin_shop.presenter.CartPresenter
import com.example.kotlin_shop.view.ICartView
import com.example.kotlin_shop.view.recycler.CartAdapter
import kotlinx.android.synthetic.main.cart_layout.*

class CartView : AppCompatActivity(), ICartView {

    private val recyclerAdapter: CartAdapter = CartAdapter()
    private val viewManager = LinearLayoutManager(this)
    private val presenter = CartPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart_layout)

        presenter.attachView(this)

        rvCart.apply {
            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = recyclerAdapter
        }

        supportActionBar?.title = getString(R.string.cart_header)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        presenter.getProducts()

        btnApply.setOnClickListener {
            val intent = Intent(this, PurchaseView::class.java)
            startActivity(intent)
        }
    }

    override fun showProducts(products: List<Product>) {
        recyclerAdapter.changeItemSource(products)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
