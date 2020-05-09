package com.example.kotlin_shop.domain.usecases

import com.example.kotlin_shop.domain.CartItem
import com.example.kotlin_shop.domain.repositories.CartItemRepository

class GetCartItemsUseCase(private val repository: CartItemRepository){
    suspend operator fun invoke(): MutableList<CartItem> = repository.getItems()
}