package com.example.kotlin_shop.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_shop.R
import com.mancj.materialsearchbar.adapter.SuggestionsAdapter

class HintsAdapter(
    context: Context,
    val onClick: (s: String) -> Unit
): SuggestionsAdapter<String, HintsAdapter.Holder>(LayoutInflater.from(context)) {
    inner class Holder(val view: View): RecyclerView.ViewHolder(view){
        fun bind(text: String){
            view.findViewById<TextView>(R.id.tvSearchedTitle).text = text
            view.setOnClickListener {
                onClick(text)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.searched_item_layout, parent, false)

        return Holder(view)
    }

    override fun onBindSuggestionHolder(suggestion: String?, holder: Holder?, position: Int) {
        holder?.bind(suggestion ?: "")
    }

    override fun getSingleViewHeight(): Int {
        return 30
    }
}