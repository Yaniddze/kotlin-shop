package com.example.kotlin_shop.presentation

import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.usecases.AddCartItemUseCase
import com.example.kotlin_shop.domain.usecases.AddViewedProductUseCase
import com.example.kotlin_shop.ui.interfaces.DetailedView
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailedPresenter @Inject constructor(

    private val cartItemAdder: AddCartItemUseCase,

    private val viewedAdder: AddViewedProductUseCase

): BasePresenter<DetailedView>(){

    fun addToCart(product: Product){
        scope.launch {
            cartItemAdder(product)
            viewState.onAddToCart()
        }
    }

    fun addToViewed(product: Product){
        scope.launch {
            viewedAdder(product)
        }
    }
}