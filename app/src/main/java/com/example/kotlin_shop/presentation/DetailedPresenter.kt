package com.example.kotlin_shop.presentation

import com.example.kotlin_shop.di.components.DaggerDetailedPresenterComponent
import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.usecases.AddCartItemUseCase
import com.example.kotlin_shop.domain.usecases.AddViewedProductUseCase
import com.example.kotlin_shop.ui.interfaces.DetailedView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import moxy.MvpPresenter
import javax.inject.Inject

class DetailedPresenter: MvpPresenter<DetailedView>(){

    init {
        DaggerDetailedPresenterComponent.create().inject(this)
    }

    @Inject
    lateinit var cartItemAdder: AddCartItemUseCase

    @Inject
    lateinit var viewedAdder: AddViewedProductUseCase

    fun addToCart(product: Product){
        runBlocking(Dispatchers.IO) {
            cartItemAdder.addCartItem(product)
        }

        viewState?.onAddToCart()
    }

    fun addToViewed(product: Product){
        runBlocking {
            viewedAdder.addViewedProduct(product)
        }
    }
}