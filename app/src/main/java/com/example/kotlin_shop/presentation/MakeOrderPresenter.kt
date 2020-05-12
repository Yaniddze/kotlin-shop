package com.example.kotlin_shop.presentation

import com.example.kotlin_shop.domain.usecases.order.AddOrderUseCase
import com.example.kotlin_shop.domain.usecases.cart.GetCartItemsUseCase
import com.example.kotlin_shop.domain.usecases.validators.ValidateNameUseCase
import com.example.kotlin_shop.domain.usecases.validators.ValidatePhoneUseCase
import com.example.kotlin_shop.domain.usecases.validators.ValidateSecondNameUseCase
import com.example.kotlin_shop.domain.usecases.validators.ValidateSerNameUseCase
import com.example.kotlin_shop.ui.interfaces.OrderView
import javax.inject.Inject

class MakeOrderPresenter @Inject constructor(

    private val cartGetter: GetCartItemsUseCase,
    private val ordersAdder: AddOrderUseCase,
    private val nameValidator: ValidateNameUseCase,
    private val serNameValidator: ValidateSerNameUseCase,
    private val secondNameValidator: ValidateSecondNameUseCase,
    private val phoneValidator: ValidatePhoneUseCase

) : BasePresenter<OrderView>() {

    fun validatePhone(phone: String) {
        val result = phoneValidator(phone)

        viewState.showErrorForPhone(result)
    }

    fun validateName(name: String) {
        val result = nameValidator(name)

        viewState.showErrorForFirstName(result)
    }

    fun validateSerName(serName: String) {
        val result = serNameValidator(serName)

        viewState.showErrorForSerName(result)
    }

    fun validateSecondName(secondName: String) {
        val result = secondNameValidator(secondName)

        viewState.showErrorForSecondName(result)
    }
}