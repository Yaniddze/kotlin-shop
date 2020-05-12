package com.example.kotlin_shop.domain.usecases.order

import com.example.kotlin_shop.domain.FullOrder
import com.example.kotlin_shop.domain.repositories.OrderRepository

class GetAllOrdersUseCase(
    private val repository: OrderRepository
) {
    suspend operator fun invoke(): List<FullOrder> = repository.getAll()
}