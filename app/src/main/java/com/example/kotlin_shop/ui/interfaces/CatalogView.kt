package com.example.kotlin_shop.ui.interfaces

import com.example.kotlin_shop.domain.Product
import moxy.MvpView
import moxy.viewstate.strategy.*

interface CatalogView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showProducts(products: MutableList<Product>)

    @StateStrategyType(SkipStrategy::class)
    fun onAddCatalogItem(product: Product)

    @StateStrategyType(SkipStrategy::class)
    fun showError(message: String)

    @StateStrategyType(SkipStrategy::class)
    fun showNetworkError()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showHints(hints: List<String>)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun onFavoriteAdded(product: Product)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun onFavoriteDeleted(product: Product)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun onFavoriteRefreshed(changedIds: List<Int>)
}