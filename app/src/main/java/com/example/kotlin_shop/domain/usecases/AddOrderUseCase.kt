package com.example.kotlin_shop.domain.usecases

import com.example.kotlin_shop.domain.Order
import com.example.kotlin_shop.domain.repositories.OrderRepository

class AddOrderUseCase(
    private val repository: OrderRepository
) {
    suspend operator fun invoke(order: Order) = repository.addOrder(order)
}