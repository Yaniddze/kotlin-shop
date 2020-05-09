package com.example.kotlin_shop.data.repositories

import com.example.kotlin_shop.data.dao.ProductsDao
import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.repositories.CatalogRepository
import java.lang.Exception

class CatalogRepositoryRetrofit(

    private val dao: ProductsDao

): CatalogRepository {

    override suspend fun getCatalog(): MutableList<Product>{
        val items = dao.allProducts()

        return items.toMutableList()
    }

    override suspend fun addItem(product: Product) {
        dao.addProduct(product)
    }

    override suspend fun getById(id: String): Product? {
        return try {
            dao.getById(id)
        } catch (e: Exception){
            null
        } ?: return null
    }

    override suspend fun getHints(query: String, maxSize: Int): List<String> {
        return dao.getHints(query, maxSize)
    }
}