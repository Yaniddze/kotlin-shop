package com.example.kotlin_shop.presenter

import com.example.kotlin_shop.domain.ProductFactory
import com.example.kotlin_shop.view.interfaces.ICatalogView
import moxy.MvpPresenter

class CatalogPresenter : MvpPresenter<ICatalogView>() {

    private val factory = ProductFactory()

    val images = listOf<String>(
        "https://www.imago-images.de/imagoextern/bilder/stockfotos/imago-images-geniale-luftaufnahmen.jpg",
        "https://hindutrend.com/wp-content/uploads/2020/01/good-night-romantic-images-hd.jpg",
        "https://thumbor.forbes.com/thumbor/960x0/https%3A%2F%2Fblogs-images.forbes.com%2Fstartswithabang%2Ffiles%2F2019%2F11%2FBedin-Cover.jpg",
        "https://www.whoa.in/201604-Whoa/0-mahakal-bholenath-lord-shiva-mahadev-hd-mobile-wallpapers-images.jpg",
        "https://static.toiimg.com/thumb/msid-74343235,width-640,resizemode-4/74343235.jpg",
        "https://i.pinimg.com/736x/14/d1/c0/14d1c0fd755f10391c7b4fa62fccf754.jpg"

    )

    private val myDataSet = mutableListOf(
        factory.createProduct(
            0,
            "someProd0",
            images.random(),
            1200.0,
            0
        ),
        factory.createProduct(
            1,
            "someProd1",
            images.random(),
            1200.0,
            0
        ),
        factory.createProduct(
            2,
            "someProd2",
            images.random(),
            1200.0,
            0
        ),
        factory.createProduct(
            3,
            "someProd3",
            images.random(),
            1200.0,
            0
        ),
        factory.createProduct(
            4,
            "someProd4",
            images.random(),
            1200.0,
            0
        ),
        factory.createProduct(
            5,
            "someProd5",
            images.random(),
            1200.0,
            0
        ),
        factory.createProduct(
            6,
            "someProd6",
            images.random(),
            1200.0,
            0
        ),
        factory.createProduct(
            7,
            "someProd7",
            images.random(),
            1200.0,
            0
        ),
        factory.createProduct(
            8,
            "someProd8",
            images.random(),
            1200.0,
            0
        ),
        factory.createProduct(
            9,
            "someProd9",
            images.random(),
            1200.0,
            0
        ),
        factory.createProduct(
            10,
            "someProd10",
            images.random(),
            1200.0,
            0
        ),
        factory.createProduct(
            11,
            "someProd11",
            images.random(),
            1200.0,
            0
        )
    )

    private var i = 11

    fun addItem(){
        viewState?.onAddItem(factory.createProduct(
            ++i,
            "someProd$i",
            images.random(),
            1200.0,
            0
        ))
    }


    fun getProducts() {
        viewState?.showProducts(myDataSet)
    }
}