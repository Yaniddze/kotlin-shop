package com.example.kotlin_shop.data.dao

import com.example.kotlin_shop.domain.SubCategory
import retrofit2.http.GET

interface GenresDao {
    @GET("genres/all/Zinevich")
    suspend fun getAllGenres():List<SubCategory>
}