package com.example.kotlin_shop.presentation

import com.example.kotlin_shop.domain.usecases.order.GetAllOrdersUseCase
import com.example.kotlin_shop.ui.interfaces.OrdersView
import javax.inject.Inject

class OrdersPresenter @Inject constructor(
    private val getAllOrdersUseCase: GetAllOrdersUseCase
): BasePresenter<OrdersView>() {
    fun getOrders(){
        launch {
            val items = getAllOrdersUseCase()
            viewState.showOrders(items)
        }
    }
}