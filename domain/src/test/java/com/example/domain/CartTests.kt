package com.example.domain

import org.junit.Test

class CartTests {
    private val productFactory = ProductFactory()
    private val cartFactory = CartFactory()

    @Test
    fun create_cart_success() {

        val cart = cartFactory.createCart(
            mutableSetOf(
                productFactory.createProduct(1, "123 ", "", 1200.0, 0),
                productFactory.createProduct(2, "123 ", "", 1200.0, 0),
                productFactory.createProduct(3, "123 ", "", 1200.0, 0),
                productFactory.createProduct(4, "123 ", "", 1200.0, 0),
                productFactory.createProduct(5, "123 ", "", 1200.0, 0),
                productFactory.createProduct(6, "123 ", "", 1200.0, 0),
                productFactory.createProduct(7, "123 ", "", 1200.0, 0)
            )
        )

        assert(cart.getItems().isNotEmpty())
    }

    @Test
    fun delete_cart_item_success() {

        val productToDelete = productFactory.createProduct(4, "123 ", "", 1200.0, 0)


        val cart = cartFactory.createCart(
            mutableSetOf(
                productFactory.createProduct(1, "123 ", "", 1200.0, 0),
                productFactory.createProduct(2, "123 ", "", 1200.0, 0),
                productFactory.createProduct(3, "123 ", "", 1200.0, 0),
                productFactory.createProduct(5, "123 ", "", 1200.0, 0),
                productFactory.createProduct(6, "123 ", "", 1200.0, 0),
                productFactory.createProduct(7, "123 ", "", 1200.0, 0),
                productToDelete
            )
        )

        val countBefore = cart.getItems().size

        cart.deleteItem(productToDelete)

        assert(cart.getItems().indexOf(productToDelete) == -1)
        // Deleted all 3 objects
        assert(countBefore == cart.getItems().size + 1)
    }

    @Test
    fun add_cart_item_success() {

        val productToAdd = productFactory.createProduct(4, "123 ", "", 1200.0, 0)

        val cart = cartFactory.createCart(
            mutableSetOf(
                productFactory.createProduct(1, "123 ", "", 1200.0, 0),
                productFactory.createProduct(2, "123 ", "", 1200.0, 0),
                productFactory.createProduct(3, "123 ", "", 1200.0, 0),
                productFactory.createProduct(5, "123 ", "", 1200.0, 0),
                productFactory.createProduct(6, "123 ", "", 1200.0, 0),
                productFactory.createProduct(7, "123 ", "", 1200.0, 0)
            )
        )

        val countBefore = cart.getItems().size

        cart.addProduct(productToAdd)

        assert(cart.getItems().indexOf(productToAdd) != -1)

        // Added one object
        assert(countBefore == cart.getItems().size - 1)
    }

    @Test
    fun add_existing_into_cart_failed() {

        val productToAdd = productFactory.createProduct(4, "123 ", "", 1200.0, 0)

        val cart = cartFactory.createCart(
            mutableSetOf(
                productFactory.createProduct(1, "123 ", "", 1200.0, 0),
                productFactory.createProduct(2, "123 ", "", 1200.0, 0),
                productFactory.createProduct(3, "123 ", "", 1200.0, 0),
                productFactory.createProduct(5, "123 ", "", 1200.0, 0),
                productFactory.createProduct(6, "123 ", "", 1200.0, 0),
                productFactory.createProduct(7, "123 ", "", 1200.0, 0),
                productToAdd
            )
        )

        val countBefore = cart.getItems().size

        cart.addProduct(productToAdd)

        // Object wasn't add
        assert(countBefore == cart.getItems().size)
    }

    @Test
    fun cart_make_purchase() {
        val cart = cartFactory.createCart(
            mutableSetOf(
                productFactory.createProduct(1, "123 ", "", 1200.0, 0),
                productFactory.createProduct(2, "123 ", "", 1200.0, 0),
                productFactory.createProduct(3, "123 ", "", 1200.0, 0),
                productFactory.createProduct(5, "123 ", "", 1200.0, 0),
                productFactory.createProduct(6, "123 ", "", 1200.0, 0),
                productFactory.createProduct(7, "123 ", "", 1200.0, 0)
            )
        )

        val sizeBefore = cart.getItems().size

        val purchase: Purchase = cart.makePurchase()

        // Created purchase object
        assert(purchase.items.size == sizeBefore)

        // Clear cart
        assert(cart.getItems().isEmpty())
    }
}