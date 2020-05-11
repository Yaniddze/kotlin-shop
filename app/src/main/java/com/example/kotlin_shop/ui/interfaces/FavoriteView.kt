package com.example.kotlin_shop.ui.interfaces

import com.example.kotlin_shop.domain.FavoriteProduct
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface FavoriteView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showFavorites(favorites: List<FavoriteProduct>)

}