package com.example.kotlin_shop.domain

import java.io.Serializable
import java.text.DecimalFormat

data class Product (

    val id: String,
    val name: String,
    val price: Double,
    val discountPercent: Int,
    val description: String,
    val imageUrl: String,
    val attributes: List<Attribute>,
    val category: SubCategory,
    val otherPhotos: List<String>

    ):Serializable {
    var isFavorite = false

    companion object{
        private val format = DecimalFormat("#.##")
    }

    fun calcDiscountPrice(): Double {
        return price * (1 - (discountPercent / 100.0))
    }

    fun getRoundedFullPrice(): String = format.format(price).replace(',', '.')

    fun getRoundedDiscountPrice(): String {
        return format.format(calcDiscountPrice()).replace(',', '.')
    }
}

data class Attribute(
    val name: String,
    val value: String
)

data class SubCategory(
    val name: String,
    val mainCategory: MainCategory
)

data class MainCategory(
    val name: String
)