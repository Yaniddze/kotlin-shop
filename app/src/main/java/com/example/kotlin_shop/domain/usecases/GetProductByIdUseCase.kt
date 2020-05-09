package com.example.kotlin_shop.domain.usecases

import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.repositories.CatalogRepository
import com.example.kotlin_shop.domain.repositories.FavoriteProductsRepository

class GetProductByIdUseCase(
    private val productRepository: CatalogRepository,
    private val favoriteRepository: FavoriteProductsRepository
) {
    suspend operator fun invoke(productId: String) : Product? {
        val item = productRepository.getById(productId)
        if(item != null){
            val favorites = favoriteRepository.getAll()

            favorites.forEach {
                if(it.productId == item.id)
                    item.isFavorite = true
            }
        }
        return item
    }
}