package com.example.kotlin_shop.domain.usecases

import com.example.kotlin_shop.domain.repositories.CartItemRepository
import com.example.kotlin_shop.domain.Product

class GetCartItemsUseCase(private val repository: CartItemRepository){
    suspend operator fun invoke(): MutableList<Product> = repository.getItems()
}