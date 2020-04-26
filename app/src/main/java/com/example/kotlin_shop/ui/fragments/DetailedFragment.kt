package com.example.kotlin_shop.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.kotlin_shop.R
import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.presentation.DetailedPresenter
import com.example.kotlin_shop.ui.interfaces.DetailedView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class DetailedFragment() : MvpAppCompatFragment(R.layout.fragment_detailed), DetailedView {

    private val presenter by moxyPresenter { DetailedPresenter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageView = view.findViewById<ImageView>(R.id.ivDetailedImage)
        val titleView = view.findViewById<TextView>(R.id.tvDetailedTitle)
        val priceView = view.findViewById<TextView>(R.id.tvDetailedPrice)


        val product = arguments?.get("product") as Product

        presenter.addToViewed(product)

        Glide
            .with(imageView.context)
            .load(product.imageUrl)
            .error(R.mipmap.ic_launcher)
            .into(imageView)

        titleView.text = product.title
        priceView.text = product.lot.getRoundedPrice()

        view.findViewById<Button>(R.id.btnToCart).setOnClickListener {
            presenter.addToCart(product)
        }

    }

    override fun onAddToCart() {
        Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show()
    }
}