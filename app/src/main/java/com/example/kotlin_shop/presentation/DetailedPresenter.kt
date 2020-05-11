package com.example.kotlin_shop.presentation

import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.usecases.*
import com.example.kotlin_shop.ui.interfaces.DetailedView
import java.net.UnknownHostException
import javax.inject.Inject

class DetailedPresenter @Inject constructor(

    private val cartItemAdder: AddCartItemUseCase,

    private val viewedAdder: AddViewedProductUseCase,

    private val productGetter: GetProductByIdUseCase,

    private val viewedGetter: GetViewedProductsUseCase,

    private val favoriteAdder: AddFavoriteUseCase,

    private val favoriteDeleter: DeleteFavoriteUseCase

) : BasePresenter<DetailedView>() {

    fun addToCart(product: Product) {
        launch {
            cartItemAdder(product)
            viewState.onAddToCart()
        }
    }

    fun addToViewed(product: Product) {
        launch {
            viewedAdder(product)
        }
    }

    fun getProduct(productId: String) {
        launch {
            try {
                val product = productGetter(productId)

                if (product == null)
                    viewState.showError("Product not found")
                else {
                    viewedAdder(product)
                    viewState.showProduct(product)
                }

            } catch (e: UnknownHostException) {
                viewState.showNetworkError()
            }
        }
    }

    fun getViewed(notInclude: String) {
        launch {
            val viewed = viewedGetter()
            viewState.showViewed(viewed.filter { it.productId != notInclude }.toMutableList())
        }
    }

    fun addToFavorite(product: Product) {
        launch {
            favoriteAdder(product)
            product.isFavorite = true
            viewState.onFavoriteChanged(product)
        }
    }

    fun deleteFromFavorite(product: Product) {
        launch {
            favoriteDeleter(product)
            product.isFavorite = false
            viewState.onFavoriteChanged(product)
        }
    }
}