package com.example.kotlin_shop.di.modules

import com.example.kotlin_shop.domain.repositories.GenreRepository
import com.example.kotlin_shop.domain.usecases.genres.GetSubGenreUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SubCategoryUseCasesModule {

    @Provides
    @Singleton
    fun provideGetSubGenresUseCase(repository: GenreRepository): GetSubGenreUseCase =
        GetSubGenreUseCase(repository)

}