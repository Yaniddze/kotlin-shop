package com.example.kotlin_shop.view.interfaces

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface DetailedView: MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun onAddToCart()
}