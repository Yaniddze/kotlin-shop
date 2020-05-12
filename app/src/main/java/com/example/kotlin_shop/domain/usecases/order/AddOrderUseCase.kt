package com.example.kotlin_shop.domain.usecases.order

import com.example.kotlin_shop.domain.Order
import com.example.kotlin_shop.domain.repositories.CartItemRepository
import com.example.kotlin_shop.domain.repositories.OrderRepository

class AddOrderUseCase(
    private val orderRepository: OrderRepository,
    private val cartItemRepository: CartItemRepository
) {
    suspend operator fun invoke(order: Order) {
        order.items.forEach {
            cartItemRepository.deleteItem(it.productId)
        }
        orderRepository.addOrder(order)
    }
}