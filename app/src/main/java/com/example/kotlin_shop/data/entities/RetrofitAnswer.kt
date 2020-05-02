package com.example.kotlin_shop.data.entities

data class RetrofitAnswer(
    val id: String,
    val name: String,
    val price: Double,
    val discountPercent: Int,
    val description: String,
    val imageUrl: String,
    val attributes: List<Attribute>
)

data class Attribute(
    val name: String,
    val value: String
)