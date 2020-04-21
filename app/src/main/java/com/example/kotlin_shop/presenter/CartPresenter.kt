package com.example.kotlin_shop.presenter

import com.example.domain.ProductFactory
import com.example.kotlin_shop.view.interfaces.ICartView
import moxy.MvpPresenter

class CartPresenter: MvpPresenter<ICartView>() {

    private val factory = ProductFactory()

    private val myDataSet = listOf(
        factory.createProduct(1, "someProd0", "123321", 1200.0, 0),
        factory.createProduct(2, "someProd1", "123321", 1200.0, 0),
        factory.createProduct(3, "someProd2", "123321", 1200.0, 0),
        factory.createProduct(4, "someProd3", "123321", 1200.0, 0),
        factory.createProduct(5, "someProd4", "123321", 1200.0, 0),
        factory.createProduct(6, "someProd5", "123321", 1200.0, 0)
    )


    fun getProducts(){
        viewState?.showProducts(myDataSet)
    }
}