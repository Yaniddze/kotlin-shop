package com.example.kotlin_shop.presenter

import com.example.kotlin_shop.model.Product
import com.example.kotlin_shop.view.ICatalogView
import moxy.MvpPresenter

class CatalogPresenter : MvpPresenter<ICatalogView>() {

    private val myDataSet = listOf(
        Product("someProd0", 1200.0, 0, ""),
        Product("someProd1", 1200.0, 0, ""),
        Product("someProd2", 1200.0, 0, ""),
        Product("someProd3", 1200.0, 0, ""),
        Product("someProd4", 1200.0, 0, ""),
        Product("someProd5", 1200.0, 0, "")
    )

    fun showProducts() {
        viewState.showProducts(myDataSet)
    }
}