package com.example.kotlin_shop.ui.interfaces

import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.ViewedProduct
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface DetailedView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun onAddToCart()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProduct(product: Product)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showNetworkError()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showError(text: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showViewed(viewed: MutableList<ViewedProduct>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun onFavoriteChanged(product: Product)
}