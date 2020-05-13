package com.example.kotlin_shop.ui.adapters.catalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_shop.R
import com.example.kotlin_shop.ui.MainActivity
import com.example.kotlin_shop.ui.fragments.catalog.MainCategoryFragmentDirections

class MainCategoryAdapter : RecyclerView.Adapter<MainCategoryAdapter.Holder>() {
    inner class Holder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(text: String) {
            view.findViewById<TextView>(R.id.tvCategoryItemName).text = text

            view.setOnClickListener {
                val activity = view.context as MainActivity

                val action =
                    MainCategoryFragmentDirections.actionNavigationMainCategoryToNavigationSubCategory2(
                        text
                    )

                activity.navigate(action)
            }
        }
    }

    private var dataSet = listOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_item_layout, parent, false)

        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = dataSet[position]

        holder.bind(item)
    }

    override fun getItemCount(): Int = dataSet.size

    fun loadData(items: List<String>) {
        dataSet = items
        notifyDataSetChanged()
    }
}