package com.example.kotlin_shop.domain.factories

import com.example.kotlin_shop.domain.Lot
import com.example.kotlin_shop.domain.Product

interface ProductFactory{
    fun createProduct(
        id: Int,
        title: String,
        imageUrl: String,
        price: Double,
        salePercent: Int
    ): Product
}

class ProductFactoryImpl: ProductFactory {
    override fun createProduct(
        id: Int,
        title: String,
        imageUrl: String,
        price: Double,
        salePercent: Int
    ): Product {
        val lot = Lot(price, salePercent)
        return Product(id, title, imageUrl, lot)
    }
}