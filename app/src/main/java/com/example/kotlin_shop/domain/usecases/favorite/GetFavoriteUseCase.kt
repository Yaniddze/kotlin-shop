package com.example.kotlin_shop.domain.usecases.favorite

import com.example.kotlin_shop.domain.FavoriteProduct
import com.example.kotlin_shop.domain.repositories.FavoriteProductsRepository

class GetFavoriteUseCase(
    val repository: FavoriteProductsRepository
) {
    suspend operator fun invoke(): List<FavoriteProduct> = repository.getAll()
}