package com.example.kotlin_shop.data.dao

import com.example.kotlin_shop.data.entities.RetrofitAnswer
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsDao {

    @GET("products/all/default")
    suspend fun allProducts(): List<RetrofitAnswer>

    @GET("products/all/default/{id}")
    suspend fun getById(@Path("id") id: Int): RetrofitAnswer?

}