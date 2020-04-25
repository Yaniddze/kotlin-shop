package com.example.kotlin_shop.domain.repositories

import com.example.kotlin_shop.domain.Product

interface ViewedProductsRepository {

    suspend fun getAll(): MutableList<Product>

    suspend fun add(product: Product)

}