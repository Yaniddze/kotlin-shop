package com.example.kotlin_shop.domain.repositories

import com.example.kotlin_shop.domain.Product

interface CartItemRepository {
    suspend fun getItems(): MutableList<Product>
    suspend fun addItem(product: Product)
    suspend fun deleteItem(product: Product)
}