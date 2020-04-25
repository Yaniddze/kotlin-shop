package com.example.kotlin_shop.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_shop.R
import com.example.kotlin_shop.domain.Product
import java.util.*

class ViewedProductsAdapter: RecyclerView.Adapter<ViewedProductsAdapter.ViewHolder>() {

    inner class ViewHolder(val layout: ConstraintLayout): RecyclerView.ViewHolder(layout)

    private var dataSet: MutableList<Product> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val result = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewed_item_layout, parent, false) as ConstraintLayout

        return ViewHolder(result)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = dataSet[position]

        holder.layout.findViewById<TextView>(R.id.tvViewedId).text = product.id.toString()
        holder.layout.findViewById<TextView>(R.id.tvViewedTitle).text = product.title
        holder.layout.findViewById<TextView>(R.id.tvViewedPrice).text =
            "${product.lot.getRoundedPrice()} P"
    }

    override fun getItemCount(): Int = dataSet.size

    fun changeItemSource(products: MutableList<Product>){
        dataSet = products
        notifyDataSetChanged()
    }

}