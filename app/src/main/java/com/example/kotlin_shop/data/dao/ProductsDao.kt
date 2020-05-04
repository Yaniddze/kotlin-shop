package com.example.kotlin_shop.data.dao

import com.example.kotlin_shop.data.entities.RetrofitAnswer
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductsDao {

    @GET("products/all/default")
    suspend fun allProducts(): List<RetrofitAnswer>

    @GET("products/all/default/{id}")
    suspend fun getById(@Path("id") id: Int): RetrofitAnswer?

    @POST("products/all/{author}/")
    suspend fun addProduct(@Path("author") author: String, @Body request: RetrofitAnswer)

    @GET("products/hints/{author}/{query}/{maxSize}")
    suspend fun getHints(
        @Path("author") author: String,
        @Path("query") query: String,
        @Path("maxSize") maxSize: Int
    ): List<String>
}