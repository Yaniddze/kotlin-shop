package com.example.kotlin_shop.ui.fragments.catalog

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.kotlin_shop.R
import kotlinx.android.synthetic.main.search_active_layout.*

class ActiveSearchFragment(
    private val onDisable: () -> Unit,
    private val getProducts: () -> Unit,
    private val getHints: (query: String) -> Unit,
    private val searchAdapter: ArrayAdapter<String>
) : Fragment(R.layout.search_active_layout) {
    companion object {
        var query = ""
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivBackButton.setOnClickListener {
            query = ""
            onDisable()
        }

        ivCloseButton.setOnClickListener {
            actvSearch.setText("")
            query = ""
        }

        actvSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                query = s.toString()
                getHints(query)
            }
        })
        actvSearch.setText(query)
        actvSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                getProducts()
                true
            } else
                false
        }
        actvSearch.setAdapter(searchAdapter)
        actvSearch.requestFocus()
    }
}