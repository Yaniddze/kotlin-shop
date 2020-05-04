package com.example.kotlin_shop.domain.usecases

import com.example.kotlin_shop.domain.repositories.CatalogRepository

class GetHintsUseCase(val repository: CatalogRepository) {
    suspend operator fun invoke(
        author: String,
        query: String,
        maxSize: Int
    ): List<String> = repository.getHints(author, query, maxSize)
}