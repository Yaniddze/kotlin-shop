package com.example.kotlin_shop.presentation

import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.ui.interfaces.CatalogView
import org.junit.Test

class CatalogPresenterTests {
    private class TestClass:
        CatalogView {
        var dataSet = mutableListOf<Product>()

        override fun showProducts(products: MutableList<Product>) {
            dataSet = products
        }

        override fun onAddCatalogItem() {
            TODO("Not yet implemented")
        }

        override fun showViewed(products: MutableList<Product>) {
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