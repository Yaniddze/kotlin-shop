package com.example.kotlin_shop.presenter

import com.example.kotlin_shop.di.DaggerCartItemRepositoryComponent
import com.example.kotlin_shop.domain.CartItemRepository
import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.view.interfaces.DetailedView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import moxy.MvpPresenter
import javax.inject.Inject

class DetailedPresenter: MvpPresenter<DetailedView>(){

    init {
        DaggerCartItemRepositoryComponent.create().inject(this)
    }

    @Inject
    lateinit var cartRepository: CartItemRepository

    fun addToCart(product: Product){
        runBlocking(Dispatchers.IO) {
            cartRepository.addItem(product)
        }

        viewState?.onAddToCart()
    }
}