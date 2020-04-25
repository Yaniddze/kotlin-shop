package com.example.kotlin_shop.domain.usecases

import com.example.kotlin_shop.domain.Product

interface DeleteCartItemUseCase {
    suspend fun deleteCartItem(product: Product)
}