package com.example.kotlin_shop.domain.usecases

import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.repositories.CartItemRepository

class AddCartItemUseCaseImpl(private val repository: CartItemRepository): AddCartItemUseCase {
    override suspend fun addCartItem(product: Product) = repository.addItem(product)
}