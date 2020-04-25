package com.example.kotlin_shop.domain.usecases

import com.example.kotlin_shop.domain.Product

interface GetViewedProductsUseCase {
    suspend fun getViewedProducts(): MutableList<Product>
}