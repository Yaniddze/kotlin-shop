package com.example.kotlin_shop.presentation

import com.example.kotlin_shop.domain.usecases.favorite.GetFavoriteUseCase
import com.example.kotlin_shop.ui.interfaces.FavoriteView
import javax.inject.Inject

class FavoritePresenter @Inject constructor(

    private val favoriteGetter: GetFavoriteUseCase

) : BasePresenter<FavoriteView>() {

    fun getFavorites() {
        launch {
            val items = favoriteGetter()

            viewState.showFavorites(items)
        }
    }
}