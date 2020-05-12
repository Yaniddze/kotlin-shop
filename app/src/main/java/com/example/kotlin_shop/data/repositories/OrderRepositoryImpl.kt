package com.example.kotlin_shop.data.repositories

import com.example.kotlin_shop.data.dao.OrderDao
import com.example.kotlin_shop.domain.FullOrder
import com.example.kotlin_shop.domain.Order
import com.example.kotlin_shop.domain.repositories.OrderRepository

class OrderRepositoryImpl(
    private val dao: OrderDao
): OrderRepository {

    override suspend fun addOrder(order: Order) = dao.addOrder(order)

    override suspend fun getAll(): List<FullOrder> = dao.getAll()

}