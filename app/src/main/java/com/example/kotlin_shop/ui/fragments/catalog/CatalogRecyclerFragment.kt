package com.example.kotlin_shop.ui.fragments.catalog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_shop.R
import com.example.kotlin_shop.ui.recycler.CatalogAdapter

class CatalogRecyclerFragment(
    private val catalogAdapter: CatalogAdapter
): Fragment(R.layout.fragmnet_catalog_recycler) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val catalogRecycler = view.findViewById<RecyclerView>(R.id.rvCatalog)

        val catalogManager = GridLayoutManager(context, 2)

        catalogRecycler.apply {
            setHasFixedSize(true)

            layoutManager = catalogManager

            adapter = catalogAdapter
        }
    }
}