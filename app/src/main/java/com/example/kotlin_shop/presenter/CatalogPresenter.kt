package com.example.kotlin_shop.presenter

import com.example.kotlin_shop.model.Product
import com.example.kotlin_shop.view.ICatalogView
import moxy.MvpPresenter

class CatalogPresenter : MvpPresenter<ICatalogView>() {

    private val myDataSet = listOf(
        Product("someProd0", 1200.0, 0, "https://www.imago-images.de/imagoextern/bilder/stockfotos/imago-images-geniale-luftaufnahmen.jpg"),
        Product("someProd1", 1200.0, 0, "https://hindutrend.com/wp-content/uploads/2020/01/good-night-romantic-images-hd.jpg"),
        Product("someProd2", 1200.0, 0, "https://thumbor.forbes.com/thumbor/960x0/https%3A%2F%2Fblogs-images.forbes.com%2Fstartswithabang%2Ffiles%2F2019%2F11%2FBedin-Cover.jpg"),
        Product("someProd3", 1200.0, 0, "https://www.whoa.in/201604-Whoa/0-mahakal-bholenath-lord-shiva-mahadev-hd-mobile-wallpapers-images.jpg"),
        Product("someProd4", 1200.0, 0, "https://static.toiimg.com/thumb/msid-74343235,width-640,resizemode-4/74343235.jpg"),
        Product("someProd5", 1200.0, 0, "https://i.pinimg.com/736x/14/d1/c0/14d1c0fd755f10391c7b4fa62fccf754.jpg")
    )

    fun getProducts() {
        viewState.showProducts(myDataSet)
    }
}