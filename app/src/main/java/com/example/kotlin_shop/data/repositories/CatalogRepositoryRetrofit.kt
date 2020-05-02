package com.example.kotlin_shop.data.repositories

import com.example.kotlin_shop.data.dao.ProductsDao
import com.example.kotlin_shop.domain.Attribute
import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.factories.ProductFactory
import com.example.kotlin_shop.domain.repositories.CatalogRepository
import java.lang.Exception

class CatalogRepositoryRetrofit(

    private val dao: ProductsDao,
    private val factory: ProductFactory

): CatalogRepository {

    override suspend fun getCatalog(): MutableList<Product>{
        val items = dao.allProducts()

        return items.map {
            factory.createProduct(
                it.id.toInt(),
                it.name,
                it.imageUrl,
                it.description,
                it.attributes.map { attr -> Attribute(attr.name, attr.value) },
                it.price.toDouble(),
                it.discountPercent
            )
        }.toMutableList()
    }

    override suspend fun addItem(product: Product) {
        throw Exception()
    }

    override suspend fun getById(id: Int): Product? {
        val tempProduct = dao.getById(id) ?: return null

        return factory.createProduct(
            tempProduct.id.toInt(),
            tempProduct.name,
            tempProduct.imageUrl,
            tempProduct.description,
            tempProduct.attributes.map { attr -> Attribute(attr.name, attr.value) },
            tempProduct.price.toDouble(),
            tempProduct.discountPercent
        )
    }
}