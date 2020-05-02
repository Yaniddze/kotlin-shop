package com.example.kotlin_shop.presentation

import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.usecases.AddCartItemUseCase
import com.example.kotlin_shop.domain.usecases.AddViewedProductUseCase
import com.example.kotlin_shop.domain.usecases.GetProductByIdUseCase
import com.example.kotlin_shop.domain.usecases.GetViewedProductsUseCase
import com.example.kotlin_shop.ui.interfaces.DetailedView
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

class DetailedPresenter @Inject constructor(

    private val cartItemAdder: AddCartItemUseCase,

    private val viewedAdder: AddViewedProductUseCase,

    private val productGetter: GetProductByIdUseCase,

    private val viewedGetter: GetViewedProductsUseCase

): BasePresenter<DetailedView>(){

    fun addToCart(product: Product){
        scope.launch {
            cartItemAdder(product)
            viewState.onAddToCart()
        }
    }

    fun addToViewed(product: Product){
        scope.launch {
            viewedAdder(product)
        }
    }

    fun getProduct(productId: Int){
        scope.launch {
            try{
                val product = productGetter(productId)

                if(product == null)
                    viewState.showError("Product not found")
                else
                    viewState.showProduct(product)

            }catch (e: UnknownHostException){
                viewState.showNetworkError()
            }
        }
    }

    fun getViewed(notInclude: Int){
        scope.launch {
            val viewed = viewedGetter()

            viewState.showViewed(viewed.filter { it.productId != notInclude }.toMutableList())
        }
    }
}