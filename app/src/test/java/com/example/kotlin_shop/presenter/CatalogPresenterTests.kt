package com.example.kotlin_shop.presenter

import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.view.interfaces.ICatalogView
import org.junit.Test

class CatalogPresenterTests {
    private class TestClass:
        ICatalogView {
        var dataSet = mutableListOf<Product>()

        override fun showProducts(products: MutableList<Product>) {
            dataSet = products
        }

        override fun onAddItem(product: Product) {
            TODO("Not yet implemented")
        }
    }


    @Test
    fun getCatalogSuccess(){
        val presenter = CatalogPresenter()
        val view = TestClass()

        presenter.attachView(view)

        presenter.getProducts()

        assert(view.dataSet.isNotEmpty())
    }
}