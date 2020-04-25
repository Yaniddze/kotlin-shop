package com.example.kotlin_shop.domain.usecases

import com.example.kotlin_shop.domain.Product

interface AddCartItemUseCase {
    suspend fun addCartItem(product: Product)
}