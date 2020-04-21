package com.example.kotlin_shop.presenter

import com.example.domain.Product
import com.example.kotlin_shop.view.interfaces.ICartView
import org.junit.Test

class CartPresenterTests {
    class Tester: ICartView {
        var products: List<com.example.domain.Product> = listOf()

        override fun showProducts(products: List<com.example.domain.Product>) {
            this.products = products
        }
    }

    @Test
    fun get_cart_success(){
        val presenter = CartPresenter()

        val tester = Tester()

        presenter.attachView(tester)

        presenter.getProducts()

        assert(tester.products.isNotEmpty())
    }
}