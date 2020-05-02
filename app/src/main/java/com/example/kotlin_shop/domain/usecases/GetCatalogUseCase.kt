package com.example.kotlin_shop.domain.usecases

import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.repositories.CatalogRepository

class GetCatalogUseCase(private val repository: CatalogRepository) {
    suspend operator fun invoke(): MutableList<Product> = repository.getCatalog()
}