package com.example.kotlin_shop.view.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin_shop.R
import com.example.kotlin_shop.model.Product

class CatalogAdapter() : RecyclerView.Adapter<CatalogAdapter.ViewHolder>() {

    private var dataSet: List<Product> = listOf()

    class ViewHolder(val layout: ConstraintLayout): RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.catalog_item, parent, false) as ConstraintLayout

        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.layout.findViewById<TextView>(R.id.tvCatalogItemTitle).text = dataSet[position].title
        holder.layout.findViewById<TextView>(R.id.tvCatalogItemPrice).text = dataSet[position].calcDiscountPrice().toString()

        val imageView = holder.layout.findViewById<ImageView>(R.id.ivCatalogItemImage)

        Glide
            .with(holder.layout)
            .load(dataSet[position].imageUrl)
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(imageView)
    }

    fun changeItems(products: List<Product>){
        dataSet = products
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}