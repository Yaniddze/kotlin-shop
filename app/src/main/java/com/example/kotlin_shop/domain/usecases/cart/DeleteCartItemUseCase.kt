package com.example.kotlin_shop.domain.usecases.cart

import com.example.kotlin_shop.domain.CartItem
import com.example.kotlin_shop.domain.repositories.CartItemRepository

class DeleteCartItemUseCase(private val repository: CartItemRepository) {
    suspend operator fun invoke(item: CartItem) = repository.deleteItem(item)
}