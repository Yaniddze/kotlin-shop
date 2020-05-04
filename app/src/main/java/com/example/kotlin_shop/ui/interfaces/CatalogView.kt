package com.example.kotlin_shop.ui.interfaces

import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.ViewedProduct
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface CatalogView: MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProducts(products: MutableList<Product>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun onAddCatalogItem(product: Product)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showError(message: String)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showNetworkError()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showHints(hints: List<String>)
}