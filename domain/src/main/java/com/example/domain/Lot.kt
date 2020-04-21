package com.example.domain

class Lot internal constructor(
    /**
    * [price] must be positive
    */
    val price: Double,
    /**
    * [salePercent] must between 0 and 100
    */
    val salePercent: Int
) {
    fun calcDiscountPrice():Double{
        return price * (1 - (salePercent/100.0))
    }
}