package com.example.kotlin_shop.domain

import java.text.DecimalFormat

data class CartItem(
    val id: String,
    val title: String,
    val image: String,
    val price: Double,
    val discountPercent: Int,
    var count: Int
) {
    companion object {
        private val format = DecimalFormat("#.##")
    }

    fun calcDiscountPrice(): Double {
        return price * (1 - (discountPercent / 100.0))
    }

    fun getRoundedPrice(): String {
        return format.format(calcDiscountPrice()).replace(',', '.')
    }

    fun getRoundedFullPrice(): String {
        return format.format(price).replace(',', '.')
    }

    override fun equals(other: Any?): Boolean {
        if (other is String) {
            return other == id
        }
        if (other is Int) {
            return other == id.toInt()
        }
        if (other is CartItem) {
            return other.id == id
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return "$id ".hashCode()
    }
}