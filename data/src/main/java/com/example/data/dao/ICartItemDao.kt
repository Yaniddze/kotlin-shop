package com.example.data.dao

import androidx.room.*
import com.example.data.entities.CartItemDB

@Dao
interface ICartItemDao {

    @Query("SELECT * FROM cart_item")
    suspend fun getCartItems(): List<CartItemDB>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: CartItemDB)

    @Query("DELETE FROM cart_item WHERE product_id = :productId")
    suspend fun delete(productId: Int)

}