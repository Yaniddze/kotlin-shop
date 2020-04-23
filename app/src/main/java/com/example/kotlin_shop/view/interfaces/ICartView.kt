package com.example.kotlin_shop.view.interfaces

import com.example.domain.Product
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface ICartView: MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProducts(products: MutableList<Product>)


    @StateStrategyType(AddToEndSingleStrategy::class)
    fun onItemDeleted(product: Product)

}