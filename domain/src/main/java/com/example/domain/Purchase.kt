package com.example.domain

import java.util.*

class Purchase internal constructor(

    val items: Set<Product>,
    val date: Date

)

class PurchaseFactory{
    fun createPurchase(items: Set<Product>, date: Date): Purchase{
        return Purchase(
            items,
            date
        )
    }
}