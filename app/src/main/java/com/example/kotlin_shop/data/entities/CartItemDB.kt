package com.example.kotlin_shop.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_item")
class CartItemDB internal constructor(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    val id: Int,

    @ColumnInfo(name="product_id")
    val productId: Int,

    @ColumnInfo(name="title")
    val title: String,

    @ColumnInfo(name="image_url")
    val imageUrl: String,

    @ColumnInfo(name="price")
    val price: Double,

    @ColumnInfo(name="sale_percent")
    val salePercent: Int

)
