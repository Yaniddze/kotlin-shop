package com.example.kotlin_shop.presenter

import com.example.kotlin_shop.view.ICartView
import moxy.MvpPresenter

class CartPresenter: MvpPresenter<ICartView>() {

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
}