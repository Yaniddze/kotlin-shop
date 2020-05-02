package com.example.kotlin_shop.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlin_shop.data.entities.ViewedProductDB

@Dao
interface ViewedProductsDao {
    @Query("SELECT * FROM viewed_products ORDER BY id DESC")
    suspend fun getAll(): List<ViewedProductDB>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: ViewedProductDB)

    @Query("DELETE FROM viewed_products WHERE productId = :productId")
    suspend fun delete(productId: Int)

    @Query("DELETE FROM viewed_products")
    suspend fun deleteAll()
}