package com.example.kotlin_shop.domain.usecases

import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.repositories.CatalogRepository

class GetCatalogUseCaseImpl(
    private val repository: CatalogRepository
): GetCatalogUseCase {
    override suspend fun get(): MutableList<Product> = repository.getCatalog()
}