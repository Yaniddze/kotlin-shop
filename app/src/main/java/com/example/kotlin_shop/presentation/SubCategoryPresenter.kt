package com.example.kotlin_shop.presentation

import com.example.kotlin_shop.domain.MainCategory
import com.example.kotlin_shop.domain.SubCategory
import com.example.kotlin_shop.domain.usecases.genres.GetSubGenreUseCase
import com.example.kotlin_shop.ui.interfaces.SubCategoryView
import java.lang.Exception
import javax.inject.Inject

class SubCategoryPresenter @Inject constructor(
    private val getSubGenreUseCase: GetSubGenreUseCase
): BasePresenter<SubCategoryView>() {
    fun getSubCategory(mainCategory: String){
        launch {
            try{
                val items = getSubGenreUseCase(mainCategory)

                viewState.showGenres(items)
            }
            catch (e: Exception){
                viewState.showNetworkError()
                viewState.showGenres(listOf(SubCategory("sub 1", MainCategory("asd")), SubCategory("sub 2", MainCategory("asd"))))
            }
        }
    }
}