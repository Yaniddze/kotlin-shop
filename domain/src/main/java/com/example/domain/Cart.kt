package com.example.domain

import java.util.*

class Cart internal constructor(

    private val items: MutableSet<Product>

){

    fun getItems(): Set<Product>{
        return items
    }

    fun deleteItem(product: Product) {
        items.remove(product)
    }

    fun addProduct(product: Product) {
        items.add(product)
    }

    fun makePurchase(): Purchase {

        return Purchase(
            // Copy items
            items.map { it }.toSet(),
            Date()
        ).also {
            items.clear()
        }
    }
}

class CartFactory{
    fun createCart(items: MutableSet<Product>): Cart{
        return Cart(
            items
        )
    }
}