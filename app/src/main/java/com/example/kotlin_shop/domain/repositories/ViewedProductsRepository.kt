package com.example.kotlin_shop.domain.repositories

import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.ViewedProduct

interface ViewedProductsRepository {

    suspend fun getAll(): MutableList<ViewedProduct>

    suspend fun add(product: Product)

}