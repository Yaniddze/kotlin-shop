package com.example.kotlin_shop.domain.usecases

import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.repositories.FavoriteProductsRepository

class RefreshFavoriteUseCase(
    private val repository: FavoriteProductsRepository
) {
    suspend operator fun invoke(products: MutableList<Product>):List<Int>{
        val favorites = repository.getAll()
        val changed = mutableListOf<Int>()

        products.forEach { product ->

            val productStateBefore = product.isFavorite
            product.isFavorite = false

            for(element in favorites){

                if(product.id == element.productId){
                    product.isFavorite = true
                    break
                }

            }

            if(productStateBefore != product.isFavorite)
                changed.add(product.id.toInt())
        }

        return changed
    }
}