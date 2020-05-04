package com.example.kotlin_shop.domain.repositories

import com.example.kotlin_shop.domain.Product

interface CatalogRepository {
    suspend fun getCatalog(): MutableList<Product>
    suspend fun addItem(product: Product)
    suspend fun getById(id: Int): Product?
    suspend fun getHints(author: String, query: String, maxSize: Int): List<String>
}