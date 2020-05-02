package com.example.kotlin_shop.presentation

import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.usecases.DeleteCartItemUseCase
import com.example.kotlin_shop.domain.usecases.GetCartItemsUseCase
import com.example.kotlin_shop.ui.interfaces.CartView
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartPresenter @Inject constructor(
    private val deleter: DeleteCartItemUseCase,
    private val getter: GetCartItemsUseCase
) : BasePresenter<CartView>() {

    fun getProducts() {

        scope.launch {
            val items = getter()
            viewState.showProducts(items)
        }

    }

    fun deleteItem(product: Product) {
        scope.launch {
            deleter(product)

            viewState.onItemDeleted(product)
        }
    }
}