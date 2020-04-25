package com.example.kotlin_shop.domain.usecases

import com.example.kotlin_shop.domain.Product

interface AddViewedProductUseCase {
    suspend fun addViewedProduct(product: Product)
}