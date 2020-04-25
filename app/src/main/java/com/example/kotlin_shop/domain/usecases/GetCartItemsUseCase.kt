package com.example.kotlin_shop.domain.usecases

import com.example.kotlin_shop.domain.Product

interface GetCartItemsUseCase {
    suspend fun getItems():MutableList<Product>
}