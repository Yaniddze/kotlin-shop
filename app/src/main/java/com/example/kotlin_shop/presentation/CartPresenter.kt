package com.example.kotlin_shop.presentation

import com.example.kotlin_shop.di.components.DaggerCartPresenterComponent
import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.usecases.DeleteCartItemUseCase
import com.example.kotlin_shop.domain.usecases.GetCartItemsUseCase
import com.example.kotlin_shop.ui.interfaces.CartView
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartPresenter : BasePresenter<CartView>() {

    init {
        DaggerCartPresenterComponent.create().inject(this)
    }

    @Inject
    lateinit var deleter: DeleteCartItemUseCase

    @Inject
    lateinit var getter: GetCartItemsUseCase

    fun getProducts() {

        scope.launch {
            val items = getter.getItems()
            viewState.showProducts(items)
        }

    }

    fun deleteItem(product: Product) {
        scope.launch {
            deleter.deleteCartItem(product)

            viewState.onItemDeleted(product)
        }
    }
}