package com.example.kotlin_shop.ui.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin_shop.R
import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.ui.MainActivity
import com.example.kotlin_shop.ui.fragments.catalog.CatalogFragmentDirections

class CatalogAdapter(
    private val onFavoriteClick: (product: Product) -> Unit
) : RecyclerView.Adapter<CatalogAdapter.ViewHolder>() {

    var dataSet: MutableList<Product> = mutableListOf()

    inner class ViewHolder(private val layout: View) : RecyclerView.ViewHolder(layout){
        fun bind(product: Product){
            val layout = layout.findViewById<ConstraintLayout>(R.id.clCartItemLayout)

            layout.findViewById<TextView>(R.id.tvCatalogItemTitle).text = product.name
            layout.findViewById<TextView>(R.id.tvCatalogItemPrice).text =
                "${product.getRoundedDiscountPrice()} Р"
            val discountView = layout.findViewById<TextView>(R.id.tvCatalogDiscount)
            val fullPrice = layout.findViewById<TextView>(R.id.tvCatalogItemFullPrice)

            if(product.discountPercent > 0){
                discountView.text = "- ${product.discountPercent}%"
                discountView.background =
                    discountView.resources.getDrawable(R.drawable.sale_layout, null)

                fullPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                fullPrice.text = product.getRoundedFullPrice()
            }
            else{
                discountView.background = null
                discountView.text = ""
                fullPrice.text = ""
            }

            val picture = layout.findViewById<ImageView>(R.id.ivCatalogItemImage)

            Glide
                .with(layout)
                .load(product.imageUrl)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(picture)

            layout.setOnClickListener {
                val context = picture.context as MainActivity

                val action =
                    CatalogFragmentDirections.actionNavigationCatalogToNaigationDetailed(product.id)

                context.navigate(action)
            }

            layout.findViewById<ImageView>(R.id.ivCatalogFavorite).apply {
                background = picture.context.getDrawable(if(product.isFavorite) R.drawable.ic_favorite else R.drawable.ic_unfavorite)
                setOnClickListener {
                    onFavoriteClick(product)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.catalog_item_card, parent, false)

        return ViewHolder(layout)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = dataSet[position]

        holder.bind(product)
    }

    fun changeItemSource(products: MutableList<Product>) {
        dataSet = products
        notifyDataSetChanged()
    }

    fun addItem(product: Product){
        dataSet.add(product)
        notifyItemInserted(dataSet.size - 1)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun notifyProductChanged(product: Product) {
        val index = dataSet.indexOf(product)

        if(index != -1){
            notifyItemChanged(index)
        }
    }
}