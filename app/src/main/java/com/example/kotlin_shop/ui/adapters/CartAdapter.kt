package com.example.kotlin_shop.ui.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin_shop.R
import com.example.kotlin_shop.domain.CartItem

class CartAdapter(
    private val onDelete: (item: CartItem) -> Unit,
    private val saveCartItemCount: (productId: Int, count: Int) -> Unit
): RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    inner class ViewHolder(private val layout: View): RecyclerView.ViewHolder(layout){
        fun bind(product: CartItem){
            layout.findViewById<TextView>(R.id.tvCartItemTitle).text = product.title
            layout.findViewById<TextView>(R.id.tvCartItemPrice).text =
                "${product.getRoundedPrice()} Р"
            layout.findViewById<TextView>(R.id.tvDeleteCartItem).setOnClickListener {
                onDelete(product)
            }

            val discount = layout.findViewById<TextView>(R.id.tvCartDiscount)
            val fullPrice = layout.findViewById<TextView>(R.id.tvCartFullPrice)

            if(product.discountPercent > 0){
                discount.text = "- ${product.discountPercent}%"
                discount.background =
                    discount.resources.getDrawable(R.drawable.sale_layout, null)

                fullPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                fullPrice.text = product.getRoundedFullPrice()
            }
            else{
                discount.text = ""
                discount.background = null

                fullPrice.text = ""
            }

            val image = layout.findViewById<ImageView>(R.id.ivCartPhoto)

            Glide.with(image)
                .load(product.image)
                .error(R.drawable.ic_launcher_foreground)
                .centerCrop()
                .into(image)

            val count = layout.findViewById<EditText>(R.id.etCartCount)

            count.setText(product.count.toString())
            layout.findViewById<Button>(R.id.btnCartAddCount).setOnClickListener {
                val value = count.text.toString().toInt()
                if(value < 10){
                    saveCartItemCount(product.id.toInt(), value + 1)
                }
            }

            layout.findViewById<Button>(R.id.btnCartDeleteCount).setOnClickListener {
                val value = count.text.toString().toInt()
                if(value > 1){
                    saveCartItemCount(product.id.toInt(), value - 1)
                }
            }
        }
    }

    var dataSet: MutableList<CartItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_item_layout, parent, false)

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

    fun updateCount(productId: Int, count: Int) {
        val item = dataSet.firstOrNull { it.id == productId.toString() }

        if(item != null){
            item.count = count
            notifyItemChanged(dataSet.indexOf(item))
        }
    }
}