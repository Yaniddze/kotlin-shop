package com.example.kotlin_shop.domain.usecases

import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.repositories.CatalogRepository

class GetProductByIdUseCase(
    val repository: CatalogRepository
) {
    suspend operator fun invoke(productId: Int) : Product? = repository.getById(productId)
}