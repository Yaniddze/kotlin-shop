package com.example.kotlin_shop.ui.interfaces

import com.example.kotlin_shop.domain.CartItem
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface CartView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProducts(products: MutableList<CartItem>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun onItemDeleted(item: CartItem)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun onUpdateItem(productId: Int, count: Int)
}