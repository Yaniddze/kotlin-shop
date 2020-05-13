package com.example.kotlin_shop.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_shop.R
import com.example.kotlin_shop.domain.Attribute

class TechAdapter: RecyclerView.Adapter<TechAdapter.Holder>() {
    inner class Holder(private val view: View): RecyclerView.ViewHolder(view){
        fun bind(attribute: Attribute){
            view.findViewById<TextView>(R.id.tvTechTitle).text = "${attribute.name}: "
            view.findViewById<TextView>(R.id.tvTechValue).text = attribute.value
        }
    }

    private var dataSet = listOf<Attribute>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.tech_item_layout, parent, false )

        return Holder(view)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val attr = dataSet[position]

        holder.bind(attr)
    }

    fun loadItems(items: List<Attribute>){
        dataSet = items
        notifyDataSetChanged()
    }
}