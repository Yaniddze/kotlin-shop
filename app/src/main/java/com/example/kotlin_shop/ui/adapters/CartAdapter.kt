package com.example.kotlin_shop.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_shop.R
import com.example.kotlin_shop.domain.CartItem
import com.example.kotlin_shop.domain.Product

class CartAdapter(
    private val onDelete: (item: CartItem) -> Unit
): RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    class ViewHolder(val layout: ConstraintLayout): RecyclerView.ViewHolder(layout)

    private var dataSet: MutableList<CartItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_item, parent, false) as ConstraintLayout

        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]

        holder.layout.findViewById<TextView>(R.id.tvCartItemTitle).text = item.title
        holder.layout.findViewById<TextView>(R.id.tvCartItemPrice).text = item.getRoundedPrice()
        holder.layout.findViewById<TextView>(R.id.tvDeleteCartItem).setOnClickListener {
            onDelete(item)
        }
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