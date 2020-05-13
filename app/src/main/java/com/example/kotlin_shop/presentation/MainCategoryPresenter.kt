package com.example.kotlin_shop.presentation

import com.example.kotlin_shop.domain.MainCategory
import com.example.kotlin_shop.domain.usecases.genres.GetMainGenreUseCase
import com.example.kotlin_shop.ui.interfaces.MainCategoryView
import java.lang.Exception
import javax.inject.Inject

class MainCategoryPresenter @Inject constructor(

    private val getMainGenreUseCase: GetMainGenreUseCase

): BasePresenter<MainCategoryView>() {
    fun getMainGenres(){
        launch {
            try{
                val categories = getMainGenreUseCase()

                viewState.showGenres(categories)
            }
            catch (e: Exception){
                viewState.showNetworkError()
                viewState.showGenres(listOf(MainCategory("category1"), MainCategory("category2")))
            }
        }
    }
}