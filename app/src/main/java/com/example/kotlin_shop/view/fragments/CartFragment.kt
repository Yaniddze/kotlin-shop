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
import com.example.kotlin_shop.presenter.CartPresenter
import com.example.kotlin_shop.view.interfaces.ICartView
import com.example.kotlin_shop.view.MainActivity
import com.example.kotlin_shop.view.recycler.CartAdapter

class CartFragment : Fragment(R.layout.fragment_cart),
    ICartView {

    private val recyclerAdapter = CartAdapter(::onDelete)

    private val presenter = CartPresenter()

    private fun onDelete(product: Product){
        presenter.deleteItem(product)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = super.onCreateView(inflater, container, savedInstanceState)!!

        val recycler = root.findViewById<RecyclerView>(R.id.rvCart)

        val viewManager = LinearLayoutManager(recycler.context)

        val btnOrder = root.findViewById<Button>(R.id.btnApply)

        presenter.attachView(this)

        recycler.apply {
            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = recyclerAdapter
        }

        btnOrder.setOnClickListener {
            (context as MainActivity).showPurchaseFragment()
        }

        presenter.getProducts()

        return root
    }

    override fun showProducts(products: MutableList<Product>) {
        recyclerAdapter.changeItemSource(products)
    }

    override fun onItemDeleted(product: Product) {
        recyclerAdapter.deleteItem(product)
    }
}
