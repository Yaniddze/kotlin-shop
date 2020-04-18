package com.example.kotlin_shop.model

class Product(
    val title: String,
    /**
     * [price] must be positive
     */
    val price: Double,
    /**
     * [salePercent] must between 0 and 100
     */
    val salePercent: Int,
    val imageUrl: String
) {
    fun calcDiscountPrice():Double{
        return price * (1 - (salePercent/100.0))
    }
}