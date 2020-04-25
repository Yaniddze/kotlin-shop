package com.example.kotlin_shop.data.entities.factories

import com.example.kotlin_shop.data.entities.CartItemDB

interface CartItemFactory{
    fun createItem(
        productId: Int,
        title: String,
        imageUrl: String,
        price: Double,
        salePercent: Int
    ): CartItemDB
}

class CartItemFactoryImpl: CartItemFactory{
    override fun createItem(
        productId: Int,
        title: String,
        imageUrl: String,
        price: Double,
        salePercent: Int
    ): CartItemDB {
        return CartItemDB(
            0,
            productId,
            title,
            imageUrl,
            price,
            salePercent
        )
    }
}