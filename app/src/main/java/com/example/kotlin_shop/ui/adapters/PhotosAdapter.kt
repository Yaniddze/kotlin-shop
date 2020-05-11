package com.example.kotlin_shop.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin_shop.R

class PhotosAdapter: RecyclerView.Adapter<PhotosAdapter.Holder>() {
    inner class Holder(private val view: View): RecyclerView.ViewHolder(view) {
        fun bind(url: String){
            Glide.with(view)
                .load(url)
                .fitCenter()
                .error(R.drawable.ic_launcher_foreground)
                .into(view as ImageView)
        }
    }

    private var dataSet = listOf("")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.photo_detailed_layout, parent, false)

        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = dataSet[position]

        holder.bind(item)
    }

    override fun getItemCount(): Int = dataSet.size

    fun loadItems(photos: List<String>){
        dataSet = photos
        notifyDataSetChanged()
    }
}