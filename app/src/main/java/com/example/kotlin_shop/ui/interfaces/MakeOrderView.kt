package com.example.kotlin_shop.ui.interfaces

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.SkipStrategy
import moxy.viewstate.strategy.StateStrategyType

interface MakeOrderView : MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorForFirstName(visible: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorForSerName(visible: Boolean)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun showErrorForPhone(visible: Boolean)

    @StateStrategyType(SkipStrategy::class)
    fun onOrderAdded()

    @StateStrategyType(SkipStrategy::class)
    fun showMakeOrderError(errorMessage: String)
}