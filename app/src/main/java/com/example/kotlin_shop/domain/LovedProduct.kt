package com.example.kotlin_shop.domain

data class LovedProduct(
    val productId: Int,
    val imageUrl: String,
    val title: String
)

class LovedProductFactory{
    operator fun invoke(
        productId: Int,
        imageUrl: String,
        title: String
    ): LovedProduct = LovedProduct(productId, imageUrl, title)
}