package com.example.kotlin_shop.di.modules

import com.example.kotlin_shop.domain.repositories.FavoriteProductsRepository
import com.example.kotlin_shop.domain.usecases.AddFavoriteUseCase
import com.example.kotlin_shop.domain.usecases.DeleteFavoriteUseCase
import com.example.kotlin_shop.domain.usecases.GetFavoriteUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class GeneralUseCases {
    @Provides
    @Singleton
    fun provideAddFavoriteUseCase(
        repository: FavoriteProductsRepository
    ): AddFavoriteUseCase = AddFavoriteUseCase(repository)

    @Provides
    @Singleton
    fun provideDeleteFavoriteUseCase(
        repository: FavoriteProductsRepository
    ): DeleteFavoriteUseCase = DeleteFavoriteUseCase(repository)

    @Provides
    @Singleton
    fun provideGetFavoriteUseCase(
        repository: FavoriteProductsRepository
    ): GetFavoriteUseCase = GetFavoriteUseCase(repository)
}