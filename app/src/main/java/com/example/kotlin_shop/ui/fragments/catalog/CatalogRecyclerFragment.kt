package com.example.kotlin_shop.ui.fragments.catalog

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_shop.R
import com.example.kotlin_shop.ui.adapters.CatalogAdapter

class CatalogRecyclerFragment(
    private val catalogAdapter: CatalogAdapter
): Fragment(R.layout.fragmnet_catalog_recycler) {

    private lateinit var recycler: RecyclerView

    companion object{
        private var recyclerViewState: Parcelable? = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler = view.findViewById<RecyclerView>(R.id.rvCatalog)

        val catalogManager = GridLayoutManager(context, 2)

        recycler.apply {
            setHasFixedSize(true)

            layoutManager = catalogManager

            adapter = catalogAdapter
        }
    }

    override fun onStart() {
        super.onStart()


        recycler.layoutManager!!.onRestoreInstanceState(recyclerViewState)
    }
    override fun onPause() {
        super.onPause()

        recyclerViewState = recycler.layoutManager!!.onSaveInstanceState()
    }


}