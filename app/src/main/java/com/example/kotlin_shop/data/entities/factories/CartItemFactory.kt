package com.example.kotlin_shop.data.entities.factories

import com.example.kotlin_shop.data.entities.CartItemDB

class CartItemFactory{
    operator fun invoke(
        productId: Int,
        title: String,
        imageUrl: String,
        price: Double,
        salePercent: Int,
        count: Int
    ): CartItemDB {
        return CartItemDB(
            0,
            productId,
            title,
            imageUrl,
            price,
            salePercent,
            count
        )
    }
}