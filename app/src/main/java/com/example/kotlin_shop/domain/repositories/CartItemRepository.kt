package com.example.kotlin_shop.domain.repositories

import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.CartItem

interface CartItemRepository {
    suspend fun getItems(): MutableList<CartItem>
    suspend fun addItem(product: Product)
    suspend fun deleteItem(product: CartItem)
    suspend fun update(productId: Int, count: Int)
}