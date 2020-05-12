package com.example.kotlin_shop.domain.usecases.favorite

import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.repositories.FavoriteProductsRepository

class AddFavoriteUseCase(
    private val repository: FavoriteProductsRepository
) {
    suspend operator fun invoke(product: Product) = repository.insert(product)
}