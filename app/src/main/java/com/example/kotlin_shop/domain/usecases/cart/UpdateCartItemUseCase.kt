package com.example.kotlin_shop.domain.usecases.cart

import com.example.kotlin_shop.domain.repositories.CartItemRepository

class UpdateCartItemUseCase(
    private val repository: CartItemRepository
) {
    suspend operator fun invoke(productId: Int, count: Int) = repository.update(productId, count)
}