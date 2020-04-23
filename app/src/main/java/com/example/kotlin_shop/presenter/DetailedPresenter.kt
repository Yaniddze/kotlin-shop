package com.example.kotlin_shop.presenter

import com.example.kotlin_shop.data.repositories.CartItemRepository
import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.view.interfaces.IDetailedView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import moxy.MvpPresenter

class DetailedPresenter: MvpPresenter<IDetailedView>(){
    private val cartRepository =
        CartItemRepository

    fun addToCart(product: Product){
        runBlocking(Dispatchers.IO) {
            cartRepository.addItem(product)
        }

        viewState?.onAddToCart()
    }
}