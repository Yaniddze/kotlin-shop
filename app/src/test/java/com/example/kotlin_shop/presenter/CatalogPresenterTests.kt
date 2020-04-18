package com.example.kotlin_shop.presenter

import com.example.kotlin_shop.model.Product
import com.example.kotlin_shop.view.ICatalogView
import org.junit.Assert.*
import org.junit.Test

class CatalogPresenterTests {
    private class TestClass: ICatalogView{
        var dataSet = listOf<Product>()

        override fun showProducts(products: List<Product>) {
            dataSet = products
        }
    }


    @Test
    fun getCatalogSuccess(){
        val presenter = CatalogPresenter()
        val view = TestClass()

        presenter.attachView(view)

        presenter.showProducts()

        assert(view.dataSet.isNotEmpty())
    }
}