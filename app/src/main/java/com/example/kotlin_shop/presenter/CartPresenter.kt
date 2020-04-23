package com.example.kotlin_shop.presenter

import com.example.kotlin_shop.data.repositories.CartItemRepository
import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.ProductFactory
import com.example.kotlin_shop.view.interfaces.ICartView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import moxy.MvpPresenter

class CartPresenter: MvpPresenter<ICartView>() {

    private val repository =
        CartItemRepository

    private val factory = ProductFactory()

    fun getProducts(){

        lateinit var items: MutableList<Product>
        runBlocking(Dispatchers.IO){
            items = repository.getItems()
        }

        viewState?.showProducts(items)
    }

    fun deleteItem(product: Product){
        runBlocking(Dispatchers.IO){
            repository.deleteItem(product)
        }

        viewState?.onItemDeleted(product)
    }
}