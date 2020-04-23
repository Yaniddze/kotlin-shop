package com.example.kotlin_shop.data.repositories

object CartItemRepository {
    private var dao: com.example.kotlin_shop.data.dao.ICartItemDao = com.example.kotlin_shop.data.AppDatabase.getDatabase().cartItemDao()

    private val productFactory: com.example.kotlin_shop.domain.ProductFactory =
        com.example.kotlin_shop.domain.ProductFactory()
    private val cartItemFactory: com.example.kotlin_shop.data.entities.CartItemFactory =
        com.example.kotlin_shop.data.entities.CartItemFactory()

    private var items: MutableList<com.example.kotlin_shop.domain.Product>? = null

    suspend fun getItems(): MutableList<com.example.kotlin_shop.domain.Product>{
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

    suspend fun addItem(product: com.example.kotlin_shop.domain.Product){

        val index = getItems()
            .indexOf(product)

        if(index != -1)
            return

        val productToAdd: com.example.kotlin_shop.data.entities.CartItemDB = cartItemFactory.createItem(
            product.id,
            product.title,
            product.imageUrl,
            product.lot.price,
            product.lot.salePercent
        )

        dao.insert(productToAdd)
        getItems().add(product)
    }

    suspend fun deleteItem(product: com.example.kotlin_shop.domain.Product){
        dao.delete(product.id)
    }
}