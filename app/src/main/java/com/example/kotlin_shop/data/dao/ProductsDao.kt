package com.example.kotlin_shop.data.dao

import com.example.kotlin_shop.domain.Product
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProductsDao {

    @GET("products/withCategoryAndPhoto/all/Zinevich")
    suspend fun allProducts(): List<Product>

    @GET("products/withCategoryAndPhoto/all/Zinevich/{id}")
    suspend fun getById(@Path("id") id: String): Product?

    @POST("products/all/Zinevich/")
    suspend fun addProduct(@Body request: Product)

    @GET("products/hints/Zinevich/{query}/{maxSize}")
    suspend fun getHints(
        @Path("query") query: String,
        @Path("maxSize") maxSize: Int
    ): List<String>
}