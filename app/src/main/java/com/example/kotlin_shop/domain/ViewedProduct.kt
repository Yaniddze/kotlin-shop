package com.example.kotlin_shop.domain

data class ViewedProduct(
    val productId: Int,
    val imageUrl: String,
    val title: String
)

class ViewedProductFactory{
    operator fun invoke(
        productId: Int,
        imageUrl: String,
        title: String
    ): ViewedProduct = ViewedProduct(productId, imageUrl, title)
}