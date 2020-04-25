package com.example.kotlin_shop.domain.usecases

import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.repositories.CatalogRepository

class AddCatalogItemUseCaseImpl(
    private val repository: CatalogRepository
): AddCatalogItemUseCase {
    override suspend fun add(product: Product) = repository.addItem(product)
}