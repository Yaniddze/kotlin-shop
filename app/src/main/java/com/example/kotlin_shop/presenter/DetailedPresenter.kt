package com.example.kotlin_shop.presenter

import com.example.kotlin_shop.di.components.DaggerDetailedPresenterComponent
import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.usecases.AddCartItemUseCase
import com.example.kotlin_shop.view.interfaces.DetailedView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import moxy.MvpPresenter
import javax.inject.Inject

class DetailedPresenter: MvpPresenter<DetailedView>(){

    init {
        DaggerDetailedPresenterComponent.create().inject(this)
    }

    @Inject
    lateinit var adder: AddCartItemUseCase

    fun addToCart(product: Product){
        runBlocking(Dispatchers.IO) {
            adder.addCartItem(product)
        }

        viewState?.onAddToCart()
    }
}