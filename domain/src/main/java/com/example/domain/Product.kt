package com.example.domain

import java.io.Serializable

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
): Serializable {
    fun calcDiscountPrice():Double{
        return price * (1 - (salePercent/100.0))
    }
}