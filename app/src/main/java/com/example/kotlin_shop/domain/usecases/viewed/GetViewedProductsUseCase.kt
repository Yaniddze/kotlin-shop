package com.example.kotlin_shop.domain.usecases.viewed

import com.example.kotlin_shop.domain.ViewedProduct
import com.example.kotlin_shop.domain.repositories.ViewedProductsRepository

class GetViewedProductsUseCase(private val repository: ViewedProductsRepository) {

    suspend operator fun invoke(): MutableList<ViewedProduct> = repository.getAll()

}