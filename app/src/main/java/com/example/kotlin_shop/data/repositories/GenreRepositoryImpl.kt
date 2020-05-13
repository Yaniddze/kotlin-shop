package com.example.kotlin_shop.data.repositories

import com.example.kotlin_shop.data.dao.GenresDao
import com.example.kotlin_shop.domain.SubCategory
import com.example.kotlin_shop.domain.repositories.GenreRepository

class GenreRepositoryImpl(
    private val dao: GenresDao
): GenreRepository {
    override suspend fun getAllGenres(): List<SubCategory> = dao.getAllGenres()
}