package com.example.kotlin_shop.presenter

import com.example.kotlin_shop.model.Product
import com.example.kotlin_shop.view.ICartView
import moxy.MvpPresenter

class CartPresenter: MvpPresenter<ICartView>() {

    private val myDataSet = listOf(
        Product("someProd0", 1200.0, 0, ""),
        Product("someProd1", 1200.0, 0, ""),
        Product("someProd2", 1200.0, 0, ""),
        Product("someProd3", 1200.0, 0, ""),
        Product("someProd4", 1200.0, 0, ""),
        Product("someProd5", 1200.0, 0, "")
    )

    fun validatePhone(phone: String){
        val validationResult = if(phone.startsWith("8"))
            phone.length == 11
        else
            phone.startsWith("+7") && phone.length == 12

        viewState.showErrorForPhone(!validationResult)
    }

    fun validateName(name: String) {
        val result = name.length > 2

        viewState.showErrorForFirstName(!result)
    }

    fun validateSerName(serName: String) {
        val result = serName.length > 2

        viewState.showErrorForSerName(!result)
    }

    fun validateSecondName(secondName: String) {
        val result = secondName.length > 2

        viewState.showErrorForSecondName(!result)
    }

    fun getProducts(){
        viewState.showProducts(myDataSet)
    }
}