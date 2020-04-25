package com.example.kotlin_shop.domain.usecases

import com.example.kotlin_shop.domain.CartItemRepository
import com.example.kotlin_shop.domain.Product

class DeleteCartItemUseCaseImpl(
    private val repository: CartItemRepository
): DeleteCartItemUseCase {
    override suspend fun deleteCartItem(product: Product) = repository.deleteItem(product)
}