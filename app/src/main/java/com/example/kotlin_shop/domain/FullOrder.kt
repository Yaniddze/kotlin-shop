package com.example.kotlin_shop.domain

data class FullOrder(
    val userFirstName: String,
    val userLastName: String,
    val userPhone: String,
    val paymentType: Order.PaymentType,
    val items: List<Item>
) {
    data class Item(
        val product: Product,
        val count: Int
    )
}