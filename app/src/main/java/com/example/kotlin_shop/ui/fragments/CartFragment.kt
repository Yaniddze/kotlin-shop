package com.example.kotlin_shop.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_shop.R
import com.example.kotlin_shop.di.components.DaggerAppComponent
import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.presentation.CartPresenter
import com.example.kotlin_shop.presentation.CatalogPresenter
import com.example.kotlin_shop.ui.interfaces.CartView
import com.example.kotlin_shop.ui.MainActivity
import com.example.kotlin_shop.ui.recycler.CartAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class CartFragment : MvpAppCompatFragment(R.layout.fragment_cart), CartView {

    private val recyclerAdapter = CartAdapter(::onDelete)

    @Inject
    lateinit var presenterProvider: Provider<CartPresenter>

    private val presenter by moxyPresenter { presenterProvider.get() }

    init {
        DaggerAppComponent.create().inject(this)
    }

    private fun onDelete(product: Product){
        presenter.deleteItem(product)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.rvCart)

        val viewManager = LinearLayoutManager(recycler.context)

        val btnOrder = view.findViewById<Button>(R.id.btnApply)

        presenter.attachView(this)

        recycler.apply {
            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = recyclerAdapter
        }

        btnOrder.setOnClickListener {
            val action = CartFragmentDirections.actionNavigationCartToPurchaseFragment()

            (context as MainActivity).navigate(action)
        }

        presenter.getProducts()
    }

    override fun showProducts(products: MutableList<Product>) {
        recyclerAdapter.changeItemSource(products)
    }

    override fun onItemDeleted(product: Product) {
        recyclerAdapter.deleteItem(product)
    }
}