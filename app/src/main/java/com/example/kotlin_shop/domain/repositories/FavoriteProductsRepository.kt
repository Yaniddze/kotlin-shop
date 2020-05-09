package com.example.kotlin_shop.domain.repositories

import com.example.kotlin_shop.domain.FavoriteProduct
import com.example.kotlin_shop.domain.Product

interface FavoriteProductsRepository {
    suspend fun getAll(): List<FavoriteProduct>
    suspend fun insert(product: Product)
    suspend fun delete(productId: Int)
}