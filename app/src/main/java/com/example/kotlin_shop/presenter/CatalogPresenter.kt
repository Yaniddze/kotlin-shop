package com.example.kotlin_shop.presenter

import com.example.domain.ProductFactory
import com.example.kotlin_shop.view.interfaces.ICatalogView
import moxy.MvpPresenter

class CatalogPresenter : MvpPresenter<ICatalogView>() {

    private val factory = ProductFactory()

    private val myDataSet = listOf(
        factory.createProduct(
            0,
            "someProd0",
            "https://www.imago-images.de/imagoextern/bilder/stockfotos/imago-images-geniale-luftaufnahmen.jpg",
            1200.0,
            0
        ),
        factory.createProduct(
            1,
            "someProd1",
            "https://hindutrend.com/wp-content/uploads/2020/01/good-night-romantic-images-hd.jpg",
            1200.0,
            0
        ),
        factory.createProduct(
            2,
            "someProd2",
            "https://thumbor.forbes.com/thumbor/960x0/https%3A%2F%2Fblogs-images.forbes.com%2Fstartswithabang%2Ffiles%2F2019%2F11%2FBedin-Cover.jpg",
            1200.0,
            0
        ),
        factory.createProduct(
            3,
            "someProd3",
            "https://www.whoa.in/201604-Whoa/0-mahakal-bholenath-lord-shiva-mahadev-hd-mobile-wallpapers-images.jpg",
            1200.0,
            0
        ),
        factory.createProduct(
            4,
            "someProd4",
            "https://static.toiimg.com/thumb/msid-74343235,width-640,resizemode-4/74343235.jpg",
            1200.0,
            0
        ),
        factory.createProduct(
            5,
            "someProd5",
            "https://i.pinimg.com/736x/14/d1/c0/14d1c0fd755f10391c7b4fa62fccf754.jpg",
            1200.0,
            0
        )
    )

    fun getProducts() {
        viewState?.showProducts(myDataSet)
    }
}