package com.example.kotlin_shop.domain

data class ViewedProduct(
    val productId: String,
    val imageUrl: String,
    val title: String
)

class ViewedProductFactory{
    operator fun invoke(
        productId: String,
        imageUrl: String,
        title: String
    ): ViewedProduct = ViewedProduct(productId, imageUrl, title)
}