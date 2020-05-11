package com.example.kotlin_shop.data.entities

import androidx.room.*

@Entity(tableName = "viewed_products")
data class ViewedProductDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "productId")
    val productId: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "imageUrl")
    val imageUrl: String
)