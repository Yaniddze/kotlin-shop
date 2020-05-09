package com.example.kotlin_shop.domain.usecases

import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.repositories.FavoriteProductsRepository

class DeleteFavoriteUseCase(
    private val repository: FavoriteProductsRepository
) {
    suspend operator fun invoke(product: Product) = repository.delete(product.id.toInt())
}