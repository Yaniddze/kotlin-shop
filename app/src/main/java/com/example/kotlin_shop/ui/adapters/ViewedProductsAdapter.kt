package com.example.kotlin_shop.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin_shop.R
import com.example.kotlin_shop.domain.ViewedProduct
import com.example.kotlin_shop.ui.MainActivity
import com.example.kotlin_shop.ui.fragments.DetailedFragmentDirections

class ViewedProductsAdapter : RecyclerView.Adapter<ViewedProductsAdapter.ViewHolder>() {

    inner class ViewHolder(private val layout: View) : RecyclerView.ViewHolder(layout) {
        fun bind(product: ViewedProduct) {
            val image = layout.findViewById<ImageView>(R.id.ivViewedPhoto)
            layout.findViewById<TextView>(R.id.tvViewedTitle).text = product.title

            Glide.with(image.context)
                .load(product.imageUrl)
                .centerCrop()
                .error(R.drawable.ic_launcher_foreground)
                .into(image)

            layout.setOnClickListener {
                val activity = image.context as MainActivity

                val action =
                    DetailedFragmentDirections.actionNavigationDetailedSelf(product.productId)

                activity.navigate(action)
            }
        }
    }

    private var dataSet: MutableList<ViewedProduct> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val result = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewed_item_layout, parent, false)

        return ViewHolder(result)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = dataSet[position]

        holder.bind(product)
    }

    override fun getItemCount(): Int = dataSet.size

    fun changeItemSource(products: MutableList<ViewedProduct>) {
        dataSet = products
        notifyDataSetChanged()
    }
}