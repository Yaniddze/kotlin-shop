package com.example.kotlin_shop.data.repositories

import com.example.kotlin_shop.data.dao.FavoriteProductsDao
import com.example.kotlin_shop.data.entities.FavoriteProductDB
import com.example.kotlin_shop.domain.FavoriteProduct
import com.example.kotlin_shop.domain.FavoriteProductFactory
import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.repositories.FavoriteProductsRepository

class FavoriteProductsRepositoryImpl(
    private val dao: FavoriteProductsDao,
    private val factory: FavoriteProductFactory
) : FavoriteProductsRepository {

    companion object {
        private var favorites: MutableList<FavoriteProduct>? = null
    }

    override suspend fun getAll(): MutableList<FavoriteProduct> {
        if (favorites == null) {
            val items = dao.getAll()

            favorites = items.map { factory(it.productId.toString(), it.imageUrl, it.title) }.toMutableList()
        }
        return favorites!!
    }

    override suspend fun insert(product: Product) {
        val tempFavorites = getAll()

        val founded = tempFavorites.firstOrNull { it.productId == product.id }

        if (founded != null) {
            delete(founded.productId.toInt())
        }

        dao.insert(FavoriteProductDB(0, product.id.toInt(), product.imageUrl, product.name))
        tempFavorites.add(factory(product.id, product.imageUrl, product.name))
    }

    override suspend fun delete(productId: Int) {
        val tempFavorites = getAll()
        dao.delete(productId)
        tempFavorites.remove(tempFavorites.first { it.productId == productId.toString() })
    }
}