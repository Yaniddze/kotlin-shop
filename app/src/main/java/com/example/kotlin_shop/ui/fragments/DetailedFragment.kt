package com.example.kotlin_shop.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.example.kotlin_shop.R
import com.example.kotlin_shop.di.components.DaggerAppComponent
import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.presentation.CartPresenter
import com.example.kotlin_shop.presentation.DetailedPresenter
import com.example.kotlin_shop.ui.interfaces.DetailedView
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
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeRefreshLayout = view.findViewById(R.id.swipeDetailed)

        imageView = swipeRefreshLayout.findViewById(R.id.ivDetailedImage)
        titleView = swipeRefreshLayout.findViewById(R.id.tvDetailedTitle)
        priceView = swipeRefreshLayout.findViewById(R.id.tvDetailedPrice)
        btnToCart =  swipeRefreshLayout.findViewById(R.id.btnToCart)

        val productId = arguments?.get("productId") as Int

        swipeRefreshLayout.isRefreshing = true

        swipeRefreshLayout.setOnRefreshListener {
            presenter.getProduct(productId)
        }
        presenter.getProduct(productId)
    }

    override fun onAddToCart() {
        Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show()
    }

    override fun showProduct(product: Product) {
        presenter.addToViewed(product)
        swipeRefreshLayout.isRefreshing = false

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
        swipeRefreshLayout.isRefreshing = false
        Toast.makeText(context, "Network Error", Toast.LENGTH_LONG).show()
    }

    override fun showError(text: String) {
        swipeRefreshLayout.isRefreshing = false
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }
}