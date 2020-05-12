package com.example.kotlin_shop.domain.repositories

import com.example.kotlin_shop.domain.FullOrder
import com.example.kotlin_shop.domain.Order

interface OrderRepository {
    suspend fun addOrder(order: Order)
    suspend fun getAll():List<FullOrder>
}