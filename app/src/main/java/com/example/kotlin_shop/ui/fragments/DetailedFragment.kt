package com.example.kotlin_shop.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin_shop.R
import com.example.kotlin_shop.di.components.DaggerAppComponent
import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.ViewedProduct
import com.example.kotlin_shop.presentation.DetailedPresenter
import com.example.kotlin_shop.ui.interfaces.DetailedView
import com.example.kotlin_shop.ui.recycler.ViewedProductsAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class DetailedFragment() : MvpAppCompatFragment(R.layout.fragment_detailed), DetailedView {

    @Inject
    lateinit var presenterProvider: Provider<DetailedPresenter>

    private val presenter by moxyPresenter { presenterProvider.get() }

    init {
        DaggerAppComponent.create().inject(this)
    }

    private lateinit var imageView: ImageView
    private lateinit var titleView: TextView
    private lateinit var priceView: TextView
    private lateinit var btnToCart: Button

    private val recyclerAdapter = ViewedProductsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageView = view.findViewById(R.id.ivDetailedImage)
        titleView = view.findViewById(R.id.tvDetailedTitle)
        priceView = view.findViewById(R.id.tvDetailedPrice)
        btnToCart =  view.findViewById(R.id.btnToCart)

        val productId = arguments?.get("productId") as Int

        btnToCart.isActivated = false

        presenter.getProduct(productId)

        val viewedManager = GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false)

        val recycler =  view.findViewById<RecyclerView>(R.id.rvDetailedViewed)
        
        recycler.apply {
            setHasFixedSize(true)

            layoutManager = viewedManager

            adapter = recyclerAdapter
        }

        presenter.getViewed(productId)
    }

    override fun onAddToCart() {
        Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show()
    }

    override fun showProduct(product: Product) {

        btnToCart.isActivated = true

        Glide
            .with(imageView.context)
            .load(product.imageUrl)
            .error(R.mipmap.ic_launcher)
            .into(imageView)

        titleView.text = product.title
        priceView.text = product.lot.getRoundedPrice()

       btnToCart.setOnClickListener {
            presenter.addToCart(product)
        }
    }

    override fun showNetworkError() {
        Toast.makeText(context, "Network Error", Toast.LENGTH_LONG).show()
    }

    override fun showError(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }

    override fun showViewed(viewed: MutableList<ViewedProduct>) {
        recyclerAdapter.changeItemSource(viewed)
    }
}