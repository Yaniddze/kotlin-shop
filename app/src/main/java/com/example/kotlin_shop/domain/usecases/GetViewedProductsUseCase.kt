package com.example.kotlin_shop.domain.usecases

import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.repositories.ViewedProductsRepository

class GetViewedProductsUseCase(private val repository: ViewedProductsRepository) {

    suspend operator fun invoke(): MutableList<Product> = repository.getAll()

}