package com.example.kotlin_shop.domain.factories

import com.example.kotlin_shop.domain.Attribute
import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.SubCategory

class ProductFactory {
    operator fun invoke(
        id: Int,
        title: String,
        imageUrl: String,
        description: String,
        attributes: List<Attribute>,
        price: Double,
        salePercent: Int,
        category: SubCategory,
        otherPhotos: List<String>
    ): Product {
        return Product(
            id.toString(),
            title,
            price,
            salePercent,
            description,
            imageUrl,
            attributes,
            category,
            otherPhotos
        )
    }
}