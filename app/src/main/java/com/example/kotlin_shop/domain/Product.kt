package com.example.kotlin_shop.domain

import java.io.Serializable
import java.text.DecimalFormat
import java.util.*

class Product internal constructor(

    val id: Int,
    val title: String,
    val imageUrl: String,
    val lot: Lot

):Serializable {

    override fun toString(): String {
        return "$id: $title"
    }

    override fun equals(other: Any?): Boolean {
        if(other is Product){
            return other.id == id
        }
        return false
    }

    override fun hashCode(): Int {
        return "Entity$id".hashCode()
    }
}

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
    fun calcDiscountPrice(): Double {
        return price * (1 - (salePercent / 100.0))
    }

    fun getRoundedPrice(): String {
        val format = DecimalFormat("#.##")
        return format.format(calcDiscountPrice()).replace(',', '.')
    }

}