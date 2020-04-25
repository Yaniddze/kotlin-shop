package com.example.kotlin_shop.data.repositories

import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.factories.ProductFactory
import com.example.kotlin_shop.domain.repositories.CatalogRepository

class CatalogRepositoryImpl(
    factory: ProductFactory
): CatalogRepository {

    companion object{
        private var myDataSet: MutableList<Product>? = null
        private val images = listOf(
            "https://www.imago-images.de/imagoextern/bilder/stockfotos/imago-images-geniale-luftaufnahmen.jpg",
            "https://hindutrend.com/wp-content/uploads/2020/01/good-night-romantic-images-hd.jpg",
            "https://thumbor.forbes.com/thumbor/960x0/https%3A%2F%2Fblogs-images.forbes.com%2Fstartswithabang%2Ffiles%2F2019%2F11%2FBedin-Cover.jpg",
            "https://www.whoa.in/201604-Whoa/0-mahakal-bholenath-lord-shiva-mahadev-hd-mobile-wallpapers-images.jpg",
            "https://static.toiimg.com/thumb/msid-74343235,width-640,resizemode-4/74343235.jpg",
            "https://i.pinimg.com/736x/14/d1/c0/14d1c0fd755f10391c7b4fa62fccf754.jpg"
        )
    }

    init {
        if(myDataSet == null)
            myDataSet = mutableListOf(
                factory.createProduct(
                    0,
                    "someProd0",
                    images.random(),
                    randomPrice(),
                    randomPercent()
                ),
                factory.createProduct(
                    1,
                    "someProd1",
                    images.random(),
                    randomPrice(),
                    randomPercent()
                ),
                factory.createProduct(
                    2,
                    "someProd2",
                    images.random(),
                    randomPrice(),
                    randomPercent()
                ),
                factory.createProduct(
                    3,
                    "someProd3",
                    images.random(),
                    randomPrice(),
                    randomPercent()
                ),
                factory.createProduct(
                    4,
                    "someProd4",
                    images.random(),
                    randomPrice(),
                    randomPercent()
                ),
                factory.createProduct(
                    5,
                    "someProd5",
                    images.random(),
                    randomPrice(),
                    randomPercent()
                ),
                factory.createProduct(
                    6,
                    "someProd6",
                    images.random(),
                    randomPrice(),
                    randomPercent()
                ),
                factory.createProduct(
                    7,
                    "someProd7",
                    images.random(),
                    randomPrice(),
                    randomPercent()
                ),
                factory.createProduct(
                    8,
                    "someProd8",
                    images.random(),
                    randomPrice(),
                    randomPercent()
                ),
                factory.createProduct(
                    9,
                    "someProd9",
                    images.random(),
                    randomPrice(),
                    randomPercent()
                ),
                factory.createProduct(
                    10,
                    "someProd10",
                    images.random(),
                    randomPrice(),
                    randomPercent()
                ),
                factory.createProduct(
                    11,
                    "someProd11",
                    images.random(),
                    randomPrice(),
                    randomPercent()
                )
            )
    }

    private fun randomPrice() = (100..3000).random().toDouble()

    private fun randomPercent() = (0..100).random()

    override suspend fun getCatalog(): MutableList<Product> = myDataSet!!

    override suspend fun addItem(product: Product) {
        myDataSet?.add(product)
    }
}