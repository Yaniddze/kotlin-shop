package com.example.kotlin_shop.domain.usecases.genres

import com.example.kotlin_shop.domain.MainCategory
import com.example.kotlin_shop.domain.repositories.GenreRepository

class GetMainGenreUseCase(
    private val repository: GenreRepository
) {
    suspend operator fun invoke(): List<MainCategory>{
        val tempItems = repository.getAllGenres()
        val mainCategories = mutableSetOf<MainCategory>()

        tempItems.forEach {
            mainCategories.add(it.mainCategory)
        }

        return mainCategories.toList()
    }
}