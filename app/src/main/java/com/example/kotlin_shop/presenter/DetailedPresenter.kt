package com.example.kotlin_shop.presenter

import com.example.kotlin_shop.data.repositories.CartItemRepositoryImpl
import com.example.kotlin_shop.domain.CartItemRepository
import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.view.interfaces.DetailedView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import moxy.MvpPresenter

class DetailedPresenter: MvpPresenter<DetailedView>(){
    private val cartRepository: CartItemRepository = CartItemRepositoryImpl

    fun addToCart(product: Product){
        runBlocking(Dispatchers.IO) {
            cartRepository.addItem(product)
        }

        viewState?.onAddToCart()
    }
}