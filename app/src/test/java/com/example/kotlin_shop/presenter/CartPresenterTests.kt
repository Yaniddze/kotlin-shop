package com.example.kotlin_shop.presenter

import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.view.interfaces.ICartView
import org.junit.Test

class CartPresenterTests {
    class Tester: ICartView {
        var products: MutableList<Product> = mutableListOf()

        override fun showProducts(products: MutableList<Product>) {
            this.products = products
        }

        override fun onItemDeleted(product: Product) {
            TODO("Not yet implemented")
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