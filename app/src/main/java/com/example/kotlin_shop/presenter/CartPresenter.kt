package com.example.kotlin_shop.presenter

import com.example.kotlin_shop.di.components.DaggerCartPresenterComponent
import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.usecases.DeleteCartItemUseCase
import com.example.kotlin_shop.domain.usecases.GetCartItemsUseCase
import com.example.kotlin_shop.view.interfaces.CartView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import moxy.MvpPresenter
import javax.inject.Inject

class CartPresenter : MvpPresenter<CartView>() {

    init {
        DaggerCartPresenterComponent.create().inject(this)
    }

    @Inject
    lateinit var deleter: DeleteCartItemUseCase

    @Inject
    lateinit var getter: GetCartItemsUseCase

    fun getProducts() {

        lateinit var items: MutableList<Product>
        runBlocking(Dispatchers.IO) {
            items = getter.getItems()
        }

        viewState?.showProducts(items)
    }

    fun deleteItem(product: Product) {
        runBlocking(Dispatchers.IO) {
            deleter.deleteCartItem(product)
        }

        viewState?.onItemDeleted(product)
    }
}