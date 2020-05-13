package com.example.kotlin_shop.ui.interfaces

import com.example.kotlin_shop.domain.MainCategory
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

interface MainCategoryView: MvpView {

    @StateStrategyType(AddToEndStrategy::class)
    fun showGenres(categories: List<MainCategory>)

    @StateStrategyType(AddToEndStrategy::class)
    fun showNetworkError()

}