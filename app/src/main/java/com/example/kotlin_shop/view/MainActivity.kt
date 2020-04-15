package com.example.kotlin_shop.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.example.kotlin_shop.R
import com.example.kotlin_shop.model.Product
import com.example.kotlin_shop.presenter.CartPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ICartView {

    private val presenter = CartPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val product = Product("iphoneCase", 1200.0, 10)

        presenter.attachView(this)

        tvProductName.text = product.title
        tvPrice.text = product.price.toString()
        tvDiscount.text = "${product.salePercent.toString()} %"
        tvDiscountPrice.text = product.calcDiscountPrice().toString()

        setListeners()
    }

    private fun setListeners() {

        etPhone.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                presenter.validatePhone(etPhone.text.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }

        })

        etThirdName.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                presenter.validateSecondName(etThirdName.text.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }

        })

        etSecondName.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                presenter.validateSerName(etSecondName.text.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }

        })

        etFirstName.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                presenter.validateName(etFirstName.text.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }

        })
    }

    private fun EditText.showError(visible: Boolean){
        val drawable = if (visible) R.drawable.ic_error else 0

        this.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, drawable, 0)
    }

    override fun showErrorForSecondName(visible: Boolean) {
        etThirdName.showError(visible)
    }

    override fun showErrorForFirstName(visible: Boolean) {
        etFirstName.showError(visible)
    }

    override fun showErrorForSerName(visible: Boolean) {
        etSecondName.showError(visible)
    }

    override fun showErrorForPhone(visible: Boolean){
        etPhone.showError(visible)
    }
}
