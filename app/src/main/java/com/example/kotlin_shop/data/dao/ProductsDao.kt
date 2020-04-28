package com.example.kotlin_shop.data.dao

import retrofit2.http.GET

interface ProductsDao {

    @GET("products/all/default")
    suspend fun allProducts(): List<RetrofitAnswer>

}

data class RetrofitAnswer(
    val id: String,
    val name: String,
    val price: String,
    val discountPercent: Int,
    val description: String,
    val imageUrl: String,
    val attributes: List<Attribute>
)

data class Attribute(
    val name: String,
    val value: String
)