package com.example.kotlin_shop.domain.usecases

import com.example.kotlin_shop.domain.repositories.CartItemRepository
import com.example.kotlin_shop.domain.Product

class GetCartItemsUseCaseImpl(
    private val repository: CartItemRepository
): GetCartItemsUseCase {
    override suspend fun getItems(): MutableList<Product> = repository.getItems()
}