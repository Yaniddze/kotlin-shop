package com.example.kotlin_shop.ui.interfaces

import com.example.kotlin_shop.domain.SubCategory
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

interface SubCategoryView: MvpView {

    @StateStrategyType(AddToEndStrategy::class)
    fun showGenres(categories: List<SubCategory>)

    @StateStrategyType(AddToEndStrategy::class)
    fun showNetworkError()

}