package com.example.kotlin_shop.domain.usecases

import com.example.kotlin_shop.domain.repositories.GenreRepository

class GetAllGenresUseCase(
    private val repository: GenreRepository
) {
    suspend operator fun invoke() = repository.getAllGenres()
}