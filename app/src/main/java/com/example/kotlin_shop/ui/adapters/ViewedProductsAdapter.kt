package com.example.kotlin_shop.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin_shop.R
import com.example.kotlin_shop.domain.ViewedProduct
import com.example.kotlin_shop.ui.MainActivity
import com.example.kotlin_shop.ui.fragments.DetailedFragmentDirections

class ViewedProductsAdapter: RecyclerView.Adapter<ViewedProductsAdapter.ViewHolder>() {

    inner class ViewHolder(val layout: ConstraintLayout): RecyclerView.ViewHolder(layout)

    private var dataSet: MutableList<ViewedProduct> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val result = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewed_item_layout, parent, false) as ConstraintLayout

        return ViewHolder(result)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = dataSet[position]

        val image = holder.layout.findViewById<ImageView>(R.id.ivViewedPhoto)
        holder.layout.findViewById<TextView>(R.id.tvViewedTitle).text = product.title

        Glide.with(image.context)
            .load(product.imageUrl)
            .error(R.drawable.ic_launcher_foreground)
            .into(image)

        holder.layout.setOnClickListener {
            val activity = image.context as MainActivity

            val action = DetailedFragmentDirections.actionNavigationDetailedSelf(product.productId)

            activity.navigate(action)
        }
    }

    override fun getItemCount(): Int = dataSet.size

    fun changeItemSource(products: MutableList<ViewedProduct>){
        dataSet = products
        notifyDataSetChanged()
    }

}