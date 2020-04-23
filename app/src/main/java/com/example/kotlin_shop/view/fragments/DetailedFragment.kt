package com.example.kotlin_shop.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.kotlin_shop.R
import com.example.domain.Product
import com.example.kotlin_shop.presenter.DetailedPresenter
import com.example.kotlin_shop.view.interfaces.IDetailedView

class DetailedFragment():
    Fragment(R.layout.fragment_detailed), IDetailedView {

    private val presenter = DetailedPresenter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = super.onCreateView(inflater, container, savedInstanceState)!!

        presenter.attachView(this)
        val imageView = root.findViewById<ImageView>(R.id.ivDetailedImage)
        val titleView = root.findViewById<TextView>(R.id.tvDetailedTitle)
        val priceView = root.findViewById<TextView>(R.id.tvDetailedPrice)


        val product = arguments?.get("product") as Product

        Glide
            .with(imageView.context)
            .load(product.imageUrl)
            .error(R.mipmap.ic_launcher)
            .into(imageView)

        titleView.text = product.title
        priceView.text = product.lot.calcDiscountPrice().toString()

        val btnToCart = root.findViewById<Button>(R.id.btnToCart).setOnClickListener {
            presenter.addToCart(product)
        }

        return root

    }

    override fun onAddToCart() {
        Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show()
    }
}