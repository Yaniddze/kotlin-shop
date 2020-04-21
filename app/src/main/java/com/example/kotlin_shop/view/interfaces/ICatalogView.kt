package com.example.kotlin_shop.view.interfaces

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface ICatalogView: MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProducts(products: List<com.example.domain.Product>)
}