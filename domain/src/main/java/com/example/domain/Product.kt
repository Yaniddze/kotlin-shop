package com.example.domain

import java.io.Serializable

class Product internal constructor(

    override val id: Int,
    val title: String,
    val imageUrl: String,
    val lot: Lot

): Entity(id), Serializable {

    override fun toString(): String {
        return "$id: $title"
    }

}

class ProductFactory {
    fun createProduct(id: Int, title: String, imageUrl: String, price: Double, salePercent: Int): Product {
        val lot = Lot(price, salePercent)
        return Product(id, title, imageUrl, lot)
    }
}