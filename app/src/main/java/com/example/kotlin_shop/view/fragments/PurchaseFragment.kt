package com.example.kotlin_shop.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.kotlin_shop.R
import com.example.kotlin_shop.presenter.PurchasePresenter
import com.example.kotlin_shop.view.interfaces.IPurchaseView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class PurchaseFragment: MvpAppCompatFragment(R.layout.fragment_purchase), IPurchaseView {

    private val presenter by moxyPresenter { PurchasePresenter() }

    private lateinit var etFirstName: EditText
    private lateinit var etSecondName: EditText
    private lateinit var etThirdName: EditText
    private lateinit var etPhone: EditText
    private lateinit var btnBuy: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        etFirstName = view.findViewById(R.id.etFirstName)
        etSecondName = view.findViewById(R.id.etSecondName)
        etThirdName = view.findViewById(R.id.etThirdName)
        etPhone = view.findViewById(R.id.etPhone)
        btnBuy = view.findViewById(R.id.btnBuy)

        presenter.attachView(this)

        setListeners()
    }

    private fun setListeners() {

        etPhone.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                presenter.validatePhone(etPhone.text.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }

        })

        etThirdName.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                presenter.validateSecondName(etThirdName.text.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }

        })

        etSecondName.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                presenter.validateSerName(etSecondName.text.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }

        })

        etFirstName.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                presenter.validateName(etFirstName.text.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }

        })

        btnBuy.setOnClickListener {
            Toast.makeText(context, "Покупка...", Toast.LENGTH_LONG).show()
        }
    }

    private fun EditText.showError(visible: Boolean){
        val drawable = if (visible) R.drawable.ic_error else 0

        this.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, drawable, 0)
    }

    override fun showErrorForSecondName(visible: Boolean) {
        etThirdName.showError(!visible)
    }

    override fun showErrorForFirstName(visible: Boolean) {
        etFirstName.showError(!visible)
    }

    override fun showErrorForSerName(visible: Boolean) {
        etSecondName.showError(!visible)
    }

    override fun showErrorForPhone(visible: Boolean){
        etPhone.showError(!visible)
    }
}