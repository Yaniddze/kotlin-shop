package com.example.kotlin_shop.domain

data class FavoriteProduct(
    val productId: String,
    val imageUrl: String,
    val title: String
)

class FavoriteProductFactory{
    operator fun invoke(
        productId: String,
        imageUrl: String,
        title: String
    ): FavoriteProduct = FavoriteProduct(productId, imageUrl, title)
}