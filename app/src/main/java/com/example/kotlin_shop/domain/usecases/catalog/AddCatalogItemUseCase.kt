package com.example.kotlin_shop.domain.usecases.catalog

import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.repositories.CatalogRepository

class AddCatalogItemUseCase(private val repository: CatalogRepository) {
    suspend operator fun invoke(product: Product) = repository.addItem(product)
}