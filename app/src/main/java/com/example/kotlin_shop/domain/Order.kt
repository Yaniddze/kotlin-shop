package com.example.kotlin_shop.domain

class Order (
    val userFirstName: String,
    val userLastName: String,
    val userPhone: String,
    val paymentType: PaymentType,
    val items: List<Item>
){
    data class Item(
        val productId: String,
        val count: Int
    )

    enum class PaymentType {
        CashOnReceiving, CardOnReceiving,
    }
}

