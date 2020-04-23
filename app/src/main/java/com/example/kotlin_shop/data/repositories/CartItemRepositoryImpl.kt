package com.example.kotlin_shop.data.repositories

import com.example.kotlin_shop.data.dao.CartItemDao
import com.example.kotlin_shop.data.entities.CartItemDB
import com.example.kotlin_shop.data.entities.CartItemFactory
import com.example.kotlin_shop.domain.CartItemRepository
import com.example.kotlin_shop.domain.Product
import com.example.kotlin_shop.domain.ProductFactory
import javax.inject.Inject

class CartItemRepositoryImpl @Inject constructor(

    private val productFactory: ProductFactory,
    private val cartItemFactory: CartItemFactory,
    private val dao: CartItemDao

) : CartItemRepository {



    private var items: MutableList<Product>? = null

    override suspend fun getItems(): MutableList<Product>{
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

    override suspend fun addItem(product: Product){

        val index = getItems()
            .indexOf(product)

        if(index != -1)
            return

        val productToAdd: CartItemDB = cartItemFactory.createItem(
            product.id,
            product.title,
            product.imageUrl,
            product.lot.price,
            product.lot.salePercent
        )

        dao.insert(productToAdd)
        getItems().add(product)
    }

    override suspend fun deleteItem(product: Product){
        dao.delete(product.id)
    }
}