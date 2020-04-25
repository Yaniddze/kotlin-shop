package com.example.kotlin_shop.domain.usecases

import com.example.kotlin_shop.domain.Product

interface GetCatalogUseCase {
    suspend fun get(): MutableList<Product>
}