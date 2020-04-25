package com.example.kotlin_shop.domain.usecases

import com.example.kotlin_shop.domain.Product

interface AddCatalogItemUseCase {
    suspend fun add(product: Product)
}