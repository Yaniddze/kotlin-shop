package com.example.kotlin_shop.domain.usecases.genres

import com.example.kotlin_shop.domain.SubCategory
import com.example.kotlin_shop.domain.repositories.GenreRepository

class GetSubGenreUseCase(
    private val repository: GenreRepository
) {
    suspend operator fun invoke(mainCategory: String): List<SubCategory> {
        val items = repository.getAllGenres()

        return items.mapNotNull { if(it.mainCategory.name == mainCategory) it else null }
    }
}