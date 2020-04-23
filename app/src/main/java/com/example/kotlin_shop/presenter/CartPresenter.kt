package com.example.kotlin_shop.presenter

import com.example.kotlin_shop.di.DaggerCartItemRepositoryComponent
import com.example.kotlin_shop.domain.CartItemRepository
import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.view.interfaces.CartView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import moxy.MvpPresenter
import javax.inject.Inject

class CartPresenter : MvpPresenter<CartView>() {

    init {
        DaggerCartItemRepositoryComponent.create().inject(this)
    }

    @Inject
    lateinit var repository: CartItemRepository

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