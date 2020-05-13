package com.example.kotlin_shop.presentation

import com.example.kotlin_shop.domain.Order
import com.example.kotlin_shop.domain.usecases.order.AddOrderUseCase
import com.example.kotlin_shop.domain.usecases.cart.GetCartItemsUseCase
import com.example.kotlin_shop.domain.usecases.validators.ValidateNameUseCase
import com.example.kotlin_shop.domain.usecases.validators.ValidatePhoneUseCase
import com.example.kotlin_shop.domain.usecases.validators.ValidateSecondNameUseCase
import com.example.kotlin_shop.domain.usecases.validators.ValidateSerNameUseCase
import com.example.kotlin_shop.ui.interfaces.MakeOrderView
import javax.inject.Inject

class MakeOrderPresenter @Inject constructor(

    private val cartGetter: GetCartItemsUseCase,
    private val ordersAdder: AddOrderUseCase,
    private val nameValidator: ValidateNameUseCase,
    private val serNameValidator: ValidateSerNameUseCase,
    private val phoneValidator: ValidatePhoneUseCase

) : BasePresenter<MakeOrderView>() {

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

    fun makeOrder(
        name: String,
        serName: String,
        phone: String,
        paymentType: Order.PaymentType
    ){
        if(!nameValidator(name)
            || !serNameValidator(serName)
            || !phoneValidator(phone)
        ) {
            viewState.showMakeOrderError("Не все поля заполнены корректно")
            return
        }

        launch {
            try{
                val orderItems = cartGetter().map { it.toOrderItem() }
                if(orderItems.isNotEmpty()){
                    val order = Order(name, serName, phone, paymentType, orderItems)
                    ordersAdder(order)
                    viewState.onOrderAdded()
                }
                else{
                    viewState.showMakeOrderError("Корзина пуста!")
                }
            }
            catch (e: Exception){
                viewState.showNetworkError()
            }
        }
    }
}