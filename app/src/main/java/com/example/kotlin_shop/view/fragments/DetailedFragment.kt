package com.example.kotlin_shop.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.kotlin_shop.R
import com.example.domain.Product

class DetailedFragment(): Fragment(R.layout.fragment_detailed) {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = super.onCreateView(inflater, container, savedInstanceState)!!

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


        return root

    }
}