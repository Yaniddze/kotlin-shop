package com.example.kotlin_shop.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteProductDB(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "productId")
    val productId: Int,

    @ColumnInfo(name = "image")
    val imageUrl: String,

    @ColumnInfo(name = "title")
    val title: String
)