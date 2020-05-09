package com.example.kotlin_shop.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
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

    inner class ViewHolder(val layout: CardView) : RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.catalog_item_card, parent, false) as CardView

        return ViewHolder(layout)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val product = dataSet[position]

        val layout = holder.layout.findViewById<ConstraintLayout>(R.id.clCartItemLayout)

        layout.findViewById<TextView>(R.id.tvCatalogItemTitle).text = product.name
        layout.findViewById<TextView>(R.id.tvCatalogItemPrice).text =
            product.getRoundedDiscountPrice()

        val picture = layout.findViewById<ImageView>(R.id.ivCatalogItemImage)

        Glide
            .with(holder.layout)
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