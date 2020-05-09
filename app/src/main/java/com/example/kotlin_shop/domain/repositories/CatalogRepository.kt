package com.example.kotlin_shop.domain.repositories

import com.example.kotlin_shop.domain.Product

interface CatalogRepository {
    suspend fun getCatalog(): MutableList<Product>
    suspend fun addItem(product: Product)
    suspend fun getById(id: String): Product?
    suspend fun getHints(query: String, maxSize: Int): List<String>
}