package com.example.kotlin_shop.presenter

import com.example.domain.Product
import com.example.kotlin_shop.view.interfaces.ICatalogView
import org.junit.Test

class CatalogPresenterTests {
    private class TestClass:
        ICatalogView {
        var dataSet = listOf<com.example.domain.Product>()

        override fun showProducts(products: List<com.example.domain.Product>) {
            dataSet = products
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