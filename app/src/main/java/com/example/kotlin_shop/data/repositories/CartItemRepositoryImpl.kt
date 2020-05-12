package com.example.kotlin_shop.data.repositories

import com.example.kotlin_shop.data.dao.CartItemDao
import com.example.kotlin_shop.data.entities.CartItemDB
import com.example.kotlin_shop.data.entities.factories.CartItemFactory
import com.example.kotlin_shop.domain.CartItem
import com.example.kotlin_shop.domain.repositories.CartItemRepository
import com.example.kotlin_shop.domain.Product
import javax.inject.Inject

class CartItemRepositoryImpl @Inject constructor(

    private val cartItemFactory: CartItemFactory,
    private val dao: CartItemDao

) : CartItemRepository {


    private var items: MutableList<CartItem>? = null

    override suspend fun getItems(): MutableList<CartItem> {
        if (items == null) {
            val itemsDB = dao.getCartItems()

            items = itemsDB.map {
                CartItem(
                    it.productId.toString(),
                    it.title,
                    it.imageUrl,
                    it.price,
                    it.salePercent,
                    it.count
                )
            }.toMutableList()
        }

        return items!!
    }

    override suspend fun addItem(product: Product) {

        val item = CartItem(
            product.id,
            product.name,
            product.imageUrl,
            product.price,
            product.discountPercent,
            1
        )

        val index = getItems()
            .indexOf(item)

        if (index != -1)
            return

        val productToAdd: CartItemDB = cartItemFactory(
            item.id.toInt(),
            item.title,
            item.image,
            item.price,
            item.discountPercent,
            item.count
        )

        dao.insert(productToAdd)
        getItems().add(item)
    }

    override suspend fun deleteItem(product: CartItem) {
        dao.delete(product.id.toInt())
        getItems().remove(product)
    }

    override suspend fun deleteItem(productId: String) {
        dao.delete(productId.toInt())
        getItems().remove(items!!.first { it.id == productId })
    }

    override suspend fun update(productId: Int, count: Int) {
        dao.update(productId, count)
        getItems().first { it.id == productId.toString() }.count = count
    }
}