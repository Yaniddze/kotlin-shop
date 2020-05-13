package com.example.kotlin_shop.di.modules

import com.example.kotlin_shop.domain.repositories.GenreRepository
import com.example.kotlin_shop.domain.usecases.genres.GetMainGenreUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainCategoryPresenterUseCasesModule {
    @Provides
    @Singleton
    fun provideGetMainCategoryUseCase(
        repository: GenreRepository
    ): GetMainGenreUseCase = GetMainGenreUseCase(repository)
}