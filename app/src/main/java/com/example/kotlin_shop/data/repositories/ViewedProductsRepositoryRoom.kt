package com.example.kotlin_shop.data.repositories

import com.example.kotlin_shop.data.dao.ViewedProductsDao
import com.example.kotlin_shop.data.entities.ViewedProductDB
import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.ViewedProduct
import com.example.kotlin_shop.domain.ViewedProductFactory
import com.example.kotlin_shop.domain.repositories.ViewedProductsRepository

class ViewedProductsRepositoryRoom(
    private val dao: ViewedProductsDao,
    private val factory: ViewedProductFactory
) : ViewedProductsRepository {

    companion object {
        private var products: MutableList<ViewedProduct>? = null
    }

    override suspend fun getAll(): MutableList<ViewedProduct> {
        if (products == null) {
            val dbItems = dao.getAll()
            products = dbItems.map {
                factory(it.productId.toString(), it.imageUrl, it.title)
            }.toMutableList()
        }
//        dao.deleteAdd()

        return products!!
    }

    override suspend fun add(product: Product) {

        // Load data from db
        getAll()

        val tempProducts = products!!

        val founded = tempProducts.firstOrNull { it.productId == product.id }

        if (founded != null) {
            dao.delete(founded.productId.toInt())
            tempProducts.remove(founded)
        }

        dao.insert(ViewedProductDB(0, product.id.toInt(), product.name, product.imageUrl))
        tempProducts.add(0, factory(product.id, product.imageUrl, product.name))


        // Max - 7 viewed products
        while (tempProducts.size > 7) {
            val lastProduct = tempProducts.last()

            dao.delete(lastProduct.productId.toInt())
            tempProducts.remove(lastProduct)
        }
    }

}