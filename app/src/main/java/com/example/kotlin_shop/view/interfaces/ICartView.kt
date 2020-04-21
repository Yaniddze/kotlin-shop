package com.example.kotlin_shop.view.interfaces

import com.example.domain.Product
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface ICartView: MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProducts(products: List<com.example.domain.Product>)

}