package com.example.kotlin_shop.ui.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_shop.R

class HintAdapter(private val onClick: (title: String) -> Unit): RecyclerView.Adapter<HintAdapter.ViewHolder>() {

    inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view)

    private var dataSet = listOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.searched_item_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val title = dataSet[position]

        holder.view.findViewById<TextView>(R.id.tvSearchedTitle).text = title
        holder.view.setOnClickListener {
            onClick(title)
        }
    }

    override fun getItemCount(): Int = dataSet.size

    fun loadData(hints: List<String>){
        dataSet = hints
        notifyDataSetChanged()
    }
}