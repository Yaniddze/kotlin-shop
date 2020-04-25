package com.example.kotlin_shop.domain.usecases

import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.repositories.ViewedProductsRepository

class AddViewedProductUseCaseImpl(
    private val repository: ViewedProductsRepository
): AddViewedProductUseCase {
    override suspend fun addViewedProduct(product: Product) = repository.add(product)
}