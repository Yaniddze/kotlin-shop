package com.example.kotlin_shop.domain.usecases

import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.repositories.ViewedProductsRepository

class AddViewedProductUseCase(private val repository: ViewedProductsRepository) {
    suspend operator fun invoke(product: Product) = repository.add(product)
}