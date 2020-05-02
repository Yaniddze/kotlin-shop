package com.example.kotlin_shop.ui.recycler

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

class CatalogAdapter : RecyclerView.Adapter<CatalogAdapter.ViewHolder>() {

    private var dataSet: MutableList<Product> = mutableListOf()

    inner class ViewHolder(val layout: CardView) : RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.catalog_item_card, parent, false) as CardView

        return ViewHolder(layout)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val product = dataSet[position]

        val layout = holder.layout.findViewById<ConstraintLayout>(R.id.clCartItemLayout)

        layout.findViewById<TextView>(R.id.tvCatalogItemTitle).text = product.title
        layout.findViewById<TextView>(R.id.tvCatalogItemPrice).text =
            product.lot.getRoundedPrice()

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

            context.showDetailed(product.id)
        }

        layout.findViewById<ImageView>(R.id.ivDeleteItem).setOnClickListener {

            val id = dataSet.indexOf(product)

            if(id != -1){
                dataSet.removeAt(id)
                notifyItemRemoved(id)
            }

        }
    }

    fun changeItemSource(products: MutableList<Product>) {
        dataSet = products
        notifyDataSetChanged()
    }

    fun notifyItemAdded(){
        notifyItemInserted(dataSet.size - 1)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}