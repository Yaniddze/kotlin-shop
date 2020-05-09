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

    override suspend fun getItems(): MutableList<CartItem>{
        if(items == null){
            val itemsDB = dao.getCartItems()

            items = itemsDB.map {
                CartItem(
                    it.id.toString(),
                    it.title,
                    it.imageUrl,
                    it.price,
                    it.salePercent
                )
            }.toMutableList()
        }

        return items!!
    }

    override suspend fun addItem(product: Product){

        val item = CartItem(
            product.id,
            product.name,
            product.imageUrl,
            product.price,
            product.discountPercent
        )

        val index = getItems()
            .indexOf(item)

        if(index != -1)
            return

        val productToAdd: CartItemDB = cartItemFactory(
            item.id.toInt(),
            item.title,
            item.image,
            item.price,
            item.discountPercent
        )

        dao.insert(productToAdd)
        getItems().add(item)
    }

    override suspend fun deleteItem(product: CartItem){
        dao.delete(product.id.toInt())
    }
}