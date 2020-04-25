package com.example.kotlin_shop.domain.usecases

import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.repositories.ViewedProductsRepository

class GetViewedProductsUseCaseImpl(
    private val repository: ViewedProductsRepository
): GetViewedProductsUseCase {

    override suspend fun getViewedProducts(): MutableList<Product> = repository.getAll()

}