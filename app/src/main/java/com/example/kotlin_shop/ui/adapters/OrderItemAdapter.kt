package com.example.kotlin_shop.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin_shop.R
import com.example.kotlin_shop.domain.FullOrder

class OrderItemAdapter(
    private val dataSet: List<FullOrder.Item>
): RecyclerView.Adapter<OrderItemAdapter.Holder>() {
    inner class Holder(private val view: View): RecyclerView.ViewHolder(view){
        fun bind(product: FullOrder.Item){
            view.findViewById<TextView>(R.id.tvOrderItemName).text =
                product.product.name
            view.findViewById<TextView>(R.id.tvOrderItemPrice).text =
                product.product.getRoundedDiscountPrice()
            view.findViewById<TextView>(R.id.tvOrderItemCount).text =
                product.count.toString()
            val image = view.findViewById<ImageView>(R.id.ivOrderItemImage)

            Glide.with(image)
                .load(product.product.imageUrl)
                .centerCrop()
                .error(R.drawable.ic_launcher_foreground)
                .into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.order_item_layout, parent, false)

        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val product = dataSet[position]

        holder.bind(product)
    }

    override fun getItemCount(): Int = dataSet.size
}