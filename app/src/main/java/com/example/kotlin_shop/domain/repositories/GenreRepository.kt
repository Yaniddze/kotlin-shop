package com.example.kotlin_shop.domain.repositories

import com.example.kotlin_shop.domain.SubCategory

interface GenreRepository {
    suspend fun getAllGenres():List<SubCategory>
}