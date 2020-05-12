package com.example.kotlin_shop.presentation

import com.example.kotlin_shop.domain.CartItem
import com.example.kotlin_shop.domain.usecases.cart.DeleteCartItemUseCase
import com.example.kotlin_shop.domain.usecases.cart.GetCartItemsUseCase
import com.example.kotlin_shop.domain.usecases.cart.UpdateCartItemUseCase
import com.example.kotlin_shop.ui.interfaces.CartView
import javax.inject.Inject

class CartPresenter @Inject constructor(
    private val deleter: DeleteCartItemUseCase,
    private val getter: GetCartItemsUseCase,
    private val updater: UpdateCartItemUseCase
) : BasePresenter<CartView>() {

    fun getProducts() {

        launch {
            val items = getter()
            viewState.showProducts(items)
        }

    }

    fun deleteItem(item: CartItem) {
        launch {
            deleter(item)

            viewState.onItemDeleted(item)
        }
    }

    fun updateCartItem(productId: Int, count: Int) {
        launch {
            updater(productId, count)
            viewState.onUpdateItem(productId, count)
        }
    }
}