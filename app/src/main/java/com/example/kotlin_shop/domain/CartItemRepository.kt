package com.example.kotlin_shop.domain

interface CartItemRepository {
    suspend fun getItems(): MutableList<Product>
    suspend fun addItem(product: Product)
    suspend fun deleteItem(product: Product)
}