package com.example.kotlin_shop.presenter

import com.example.kotlin_shop.data.repositories.CartItemRepositoryImpl
import com.example.kotlin_shop.domain.CartItemRepository
import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.view.interfaces.CartView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import moxy.MvpPresenter

class CartPresenter : MvpPresenter<CartView>() {

    private val repository: CartItemRepository = CartItemRepositoryImpl

    fun getProducts() {

        lateinit var items: MutableList<Product>
        runBlocking(Dispatchers.IO) {
            items = repository.getItems()
        }

        viewState?.showProducts(items)
    }

    fun deleteItem(product: Product) {
        runBlocking(Dispatchers.IO) {
            repository.deleteItem(product)
        }

        viewState?.onItemDeleted(product)
    }
}