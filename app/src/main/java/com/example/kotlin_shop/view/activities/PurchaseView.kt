package com.example.kotlin_shop.view.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin_shop.R
import com.example.kotlin_shop.presenter.PurchasePresenter
import com.example.kotlin_shop.view.IPurchaseView
import kotlinx.android.synthetic.main.purchase_layout.*

class PurchaseView: AppCompatActivity(), IPurchaseView {

    val presenter = PurchasePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.purchase_layout)

        presenter.attachView(this)

        supportActionBar?.title = getString(R.string.purchase_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

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
            Toast.makeText(this, "Покупка...", Toast.LENGTH_LONG).show()
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

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}