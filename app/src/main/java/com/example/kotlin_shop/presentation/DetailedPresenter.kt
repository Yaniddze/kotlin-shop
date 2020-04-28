package com.example.kotlin_shop.presentation

import com.example.kotlin_shop.di.components.DaggerDetailedPresenterComponent
import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.usecases.AddCartItemUseCase
import com.example.kotlin_shop.domain.usecases.AddViewedProductUseCase
import com.example.kotlin_shop.ui.interfaces.DetailedView
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailedPresenter: BasePresenter<DetailedView>(){

    init {
        DaggerDetailedPresenterComponent.create().inject(this)
    }

    @Inject
    lateinit var cartItemAdder: AddCartItemUseCase

    @Inject
    lateinit var viewedAdder: AddViewedProductUseCase

    fun addToCart(product: Product){
        scope.launch {
            cartItemAdder.addCartItem(product)
            viewState.onAddToCart()
        }
    }

    fun addToViewed(product: Product){
        scope.launch {
            viewedAdder.addViewedProduct(product)
        }
    }
}