package com.example.kotlin_shop.model

import org.junit.Assert.*
import org.junit.Test

class ModelTests {
    @Test
    fun create_product_success(){
        val title = "123"
        val price = 1200.0
        val salePercent = 10
        val product = Product(title, price, salePercent)

        assertEquals(product.title, title)
        assertEquals(product.price, price, 0.001)
        assertEquals(product.salePercent, salePercent)
    }

    @Test
    fun calc_product_price_success(){
        val title = ""
        val price = 1200.0
        val salePercent = 10
        val product = Product(title, price, salePercent)

        val excepted = product.calcDiscountPrice()
        val actual = price * (1 - (salePercent / 100.0))

        assertEquals(actual, excepted, 0.001)
    }
}