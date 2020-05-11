package com.example.kotlin_shop.ui.fragments.catalog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.kotlin_shop.R

class DisabledSearchLayout(
    private val onActive: () -> Unit
) : Fragment(R.layout.disabled_search_layout) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.setOnClickListener {
            onActive()
        }
    }

}