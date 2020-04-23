package com.example.data.repositories

import com.example.data.AppDatabase
import com.example.data.dao.ICartItemDao
import com.example.data.entities.CartItemDB
import com.example.data.entities.CartItemFactory
import com.example.domain.Product
import com.example.domain.ProductFactory

object CartItemRepository {
    private var dao: ICartItemDao = AppDatabase.getDatabase().cartItemDao()

    private val productFactory: ProductFactory = ProductFactory()
    private val cartItemFactory: CartItemFactory = CartItemFactory()

    private var items: MutableList<Product>? = null

    suspend fun getItems(): MutableList<Product>{
        if(items == null){
            val itemsDB = dao.getCartItems()

            items = itemsDB.map {
                productFactory.createProduct(
                    it.productId,
                    it.title,
                    it.imageUrl,
                    it.price,
                    it.salePercent
                )
            }.toMutableList()
        }

        return items!!
    }

    suspend fun addItem(product: Product){

        val index = getItems().indexOf(product)

        if(index != -1)
            return

        val productToAdd:CartItemDB = cartItemFactory.createItem(
            product.id,
            product.title,
            product.imageUrl,
            product.lot.price,
            product.lot.salePercent
        )

        dao.insert(productToAdd)
        getItems().add(product)
    }

    suspend fun deleteItem(product: Product){
        dao.delete(product.id)
    }
}