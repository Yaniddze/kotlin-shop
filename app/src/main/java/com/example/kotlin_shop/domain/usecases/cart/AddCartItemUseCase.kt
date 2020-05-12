package com.example.kotlin_shop.domain.usecases.cart

import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.repositories.CartItemRepository

class AddCartItemUseCase(private val repository: CartItemRepository) {
    suspend operator fun invoke(product: Product) {
        repository.addItem(product)
    }
}