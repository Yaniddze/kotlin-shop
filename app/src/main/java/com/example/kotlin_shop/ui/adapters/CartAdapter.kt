package com.example.kotlin_shop.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_shop.R
import com.example.kotlin_shop.domain.CartItem

class CartAdapter(
    private val onDelete: (item: CartItem) -> Unit
): RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    inner class ViewHolder(private val layout: ConstraintLayout): RecyclerView.ViewHolder(layout){
        fun bind(product: CartItem){
            layout.findViewById<TextView>(R.id.tvCartItemTitle).text = product.title
            layout.findViewById<TextView>(R.id.tvCartItemPrice).text = product.getRoundedPrice()
            layout.findViewById<TextView>(R.id.tvDeleteCartItem).setOnClickListener {
                onDelete(product)
            }
        }
    }

    private var dataSet: MutableList<CartItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_item, parent, false) as ConstraintLayout

        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = dataSet[position]

        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun deleteItem(item: CartItem){
        val index = dataSet.indexOf(item)
        if(index != -1){
            dataSet.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    fun changeItemSource(products: MutableList<CartItem>){
        dataSet = products
        notifyDataSetChanged()
    }
}