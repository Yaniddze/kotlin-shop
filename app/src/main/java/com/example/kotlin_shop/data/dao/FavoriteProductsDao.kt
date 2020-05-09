package com.example.kotlin_shop.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kotlin_shop.data.entities.FavoriteProductDB

@Dao
interface FavoriteProductsDao {

    @Query("SELECT * FROM favorites")
    suspend fun getAll(): List<FavoriteProductDB>

    @Query("DELETE FROM favorites WHERE productId = :productId")
    suspend fun delete(productId: Int)

    @Insert
    suspend fun insert(product: FavoriteProductDB)

}