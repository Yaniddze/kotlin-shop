package com.example.kotlin_shop.domain.usecases

import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.repositories.CatalogRepository
import com.example.kotlin_shop.domain.repositories.FavoriteProductsRepository

class GetCatalogUseCase(
    private val catalogRepository: CatalogRepository,
    private val favoritesRepository: FavoriteProductsRepository
) {
    suspend operator fun invoke(): MutableList<Product> {

        val catalog = catalogRepository.getCatalog()
        val favorites = favoritesRepository.getAll()

        catalog.forEach { product ->
            favorites.forEach { favorite ->
                if (favorite.productId == product.id)
                    product.isFavorite = true
            }
        }

        return catalog

    }
}