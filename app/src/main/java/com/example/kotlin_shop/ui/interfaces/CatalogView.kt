package com.example.kotlin_shop.ui.interfaces

import com.example.kotlin_shop.domain.Product
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface CatalogView: MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProducts(products: MutableList<Product>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun onAddCatalogItem()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showViewed(products: MutableList<Product>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showError(message: String)
}