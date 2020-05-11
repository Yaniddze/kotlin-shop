package com.example.kotlin_shop.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin_shop.R
import com.example.kotlin_shop.domain.FavoriteProduct
import com.example.kotlin_shop.ui.MainActivity
import com.example.kotlin_shop.ui.fragments.FavoriteFragmentDirections

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.Holder>() {
    inner class Holder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(product: FavoriteProduct) {
            view.findViewById<TextView>(R.id.tvFavoriteItem).text = product.title

            val imageView = view.findViewById<ImageView>(R.id.ivFavoriteItem)

            Glide
                .with(imageView)
                .load(product.imageUrl)
                .centerCrop()
                .error(R.drawable.ic_launcher_foreground)
                .into(imageView)

            view.setOnClickListener {
                val action =
                    FavoriteFragmentDirections.actionNavigationFavoriteToNavigationDetailed(product.productId)

                val activity = imageView.context as MainActivity

                activity.navigate(action)
            }
        }
    }

    private var dataSet: List<FavoriteProduct> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.favorite_item_layout, parent, false)

        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val product = dataSet[position]

        holder.bind(product)
    }

    override fun getItemCount(): Int = dataSet.size

    fun changeItems(favorites: List<FavoriteProduct>) {
        dataSet = favorites
        notifyDataSetChanged()
    }
}