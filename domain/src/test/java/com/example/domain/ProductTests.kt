package com.example.domain

import org.junit.Assert.*
import org.junit.Test

class ProductTests {
    @Test
    fun create_product_success(){
        val title = "123"
        val imageUrl = "321"
        val id = 1
        val price = 123.0
        val salePercent = 22

        val product = ProductFactory().createProduct(id, title, imageUrl, price, salePercent)

        assertEquals(product.title, title)
        assertEquals(product.imageUrl, imageUrl)
        assertEquals(product.id, id)
        assertEquals(product.lot.salePercent, salePercent)
        assertEquals(product.lot.price, price, 0.001)
    }

    @Test
    fun equal_product_success(){
        val title = "123"
        val imageUrl = "321"
        val id = 1
        val price = 0.0
        val salePercent = 0

        val factory = ProductFactory()
        val product1 = factory.createProduct(id, title, imageUrl, price, salePercent)
        val product2 = factory.createProduct(id, title, imageUrl, price, salePercent)
        assertEquals(product1, product2)
        assert(product1 == product2)
    }

    @Test
    fun equal_product_failed(){
        val title = "123"
        val imageUrl = "321"
        val id = 1
        val price = 0.0
        val salePercent = 0

        val factory = ProductFactory()
        val product1 = factory.createProduct(id, title, imageUrl, price, salePercent)
        val product2 = factory.createProduct(id + 1, title, imageUrl, price, salePercent)
        assertNotEquals(product1, product2)
        assert(product1 != product2)
    }

    @Test
    fun calc_lot_price_success(){
        val price = 1200.0
        val salePercent = 10

        val lot = Lot(price, salePercent)

        val excepted = lot.calcDiscountPrice()
        val actual = price * (1 - (salePercent / 100.0))

        assertEquals(actual, excepted, 0.001)
    }
}