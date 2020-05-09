package com.example.kotlin_shop.domain

import java.text.DecimalFormat

data class CartItem(
    val id: String,
    val title: String,
    val image: String,
    val price: Double,
    val discountPercent: Int
){
    private fun calcDiscountPrice(): Double {
        return price * (1 - (discountPercent / 100.0))
    }

    fun getRoundedPrice(): String {
        val format = DecimalFormat("#.##")
        return format.format(calcDiscountPrice()).replace(',', '.')
    }
}