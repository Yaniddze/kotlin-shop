package com.example.kotlin_shop.ui.fragments.catalog

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kotlin_shop.R
import com.example.kotlin_shop.ui.adapters.catalog.CatalogAdapter
import kotlinx.android.synthetic.main.fragmnet_catalog_recycler.*

class CatalogRecyclerFragment(
    private val catalogAdapter: CatalogAdapter
) : Fragment(R.layout.fragmnet_catalog_recycler) {


    companion object {
        private var recyclerViewState: Parcelable? = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val catalogManager = GridLayoutManager(context, 2)

        rvCatalog.apply {
            setHasFixedSize(true)

            layoutManager = catalogManager

            adapter = catalogAdapter
        }
    }

    override fun onStart() {
        super.onStart()

        rvCatalog.layoutManager!!.onRestoreInstanceState(recyclerViewState)
    }

    override fun onPause() {
        super.onPause()

        recyclerViewState = rvCatalog.layoutManager!!.onSaveInstanceState()
    }
}