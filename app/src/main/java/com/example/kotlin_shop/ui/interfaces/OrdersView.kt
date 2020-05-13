package com.example.kotlin_shop.ui.interfaces

import com.example.kotlin_shop.domain.FullOrder
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface OrdersView: MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showOrders(orders: List<FullOrder>)

    @StateStrategyType(SkipStrategy::class)
    fun showNetworkError()
}