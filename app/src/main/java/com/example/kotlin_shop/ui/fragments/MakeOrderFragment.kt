package com.example.kotlin_shop.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.kotlin_shop.App
import com.example.kotlin_shop.R
import com.example.kotlin_shop.domain.Order
import com.example.kotlin_shop.presentation.MakeOrderPresenter
import com.example.kotlin_shop.ui.MainActivity
import com.example.kotlin_shop.ui.interfaces.MakeOrderView
import kotlinx.android.synthetic.main.fragment_purchase.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class MakeOrderFragment : MvpAppCompatFragment(R.layout.fragment_purchase), MakeOrderView {

    @Inject
    lateinit var presenterProvider: Provider<MakeOrderPresenter>
    private val presenter by moxyPresenter { presenterProvider.get() }

    init {
        App.appComponent.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rgOrderPayment.check(rbOrderOnlineCard.id)

        etPhone.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                presenter.validatePhone(etPhone.text.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        etSerName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                presenter.validateSerName(etSerName.text.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        etFirstName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                presenter.validateName(etFirstName.text.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        btnBuy.setOnClickListener {
            val checked = rgOrderPayment.checkedRadioButtonId
            var paymentType = Order.PaymentType.CardOnReceiving

            if(checked == rbOrderOfflineMoney.id){
                paymentType = Order.PaymentType.CashOnReceiving
            }

            presenter.makeOrder(
                etFirstName.text.toString(),
                etSerName.text.toString(),
                etPhone.text.toString(),
                paymentType
            )
        }
    }

    private fun EditText.showError(visible: Boolean) {
        val drawable = if (visible) R.drawable.ic_error else 0

        this.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, drawable, 0)
    }

    override fun showErrorForFirstName(visible: Boolean) {
        etFirstName.showError(!visible)
    }

    override fun showErrorForSerName(visible: Boolean) {
        etSerName.showError(!visible)
    }

    override fun showErrorForPhone(visible: Boolean) {
        etPhone.showError(!visible)
    }

    override fun onOrderAdded() {
        Toast.makeText(context, "Заказ создан", Toast.LENGTH_LONG).show()
        (context as MainActivity).onBackPressed()
    }

    override fun showMakeOrderError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
    }

    override fun showNetworkError() {
        Toast.makeText(context, "Network error", Toast.LENGTH_SHORT).show()
    }
}