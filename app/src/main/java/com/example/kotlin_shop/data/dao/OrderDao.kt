package com.example.kotlin_shop.data.dao

import com.example.kotlin_shop.domain.FullOrder
import com.example.kotlin_shop.domain.Order
import retrofit2.http.GET
import retrofit2.http.POST

interface OrderDao {

    @POST("orders/new/Zinevich")
    suspend fun addOrder(order: Order)

    @GET("orders/all/Zinevich")
    suspend fun getAll(): List<FullOrder>

}