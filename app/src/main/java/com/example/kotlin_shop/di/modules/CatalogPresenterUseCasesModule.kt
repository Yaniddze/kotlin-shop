package com.example.kotlin_shop.di.modules

import com.example.kotlin_shop.domain.repositories.CatalogRepository
import com.example.kotlin_shop.domain.repositories.FavoriteProductsRepository
import com.example.kotlin_shop.domain.repositories.ViewedProductsRepository
import com.example.kotlin_shop.domain.usecases.*
import com.example.kotlin_shop.domain.usecases.catalog.AddCatalogItemUseCase
import com.example.kotlin_shop.domain.usecases.catalog.GetCatalogUseCase
import com.example.kotlin_shop.domain.usecases.favorite.RefreshFavoriteUseCase
import com.example.kotlin_shop.domain.usecases.viewed.GetViewedProductsUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CatalogPresenterUseCasesModule {

    @Provides
    @Singleton
    fun provideGetViewedProductsUseCase(
        repository: ViewedProductsRepository
    ): GetViewedProductsUseCase =
        GetViewedProductsUseCase(
            repository
        )

    @Provides
    @Singleton
    fun provideMainGetCatalogUseCase(
        catalogRepository: CatalogRepository,
        favoriteProductsRepository: FavoriteProductsRepository
    ): GetCatalogUseCase =
        GetCatalogUseCase(
            catalogRepository,
            favoriteProductsRepository
        )

    @Provides
    @Singleton
    fun provideMainAddCatalogItemUseCase(
        repository: CatalogRepository
    ): AddCatalogItemUseCase =
        AddCatalogItemUseCase(
            repository
        )

    @Provides
    @Singleton
    fun provideGetHintsUseCase(
        repository: CatalogRepository
    ): GetHintsUseCase = GetHintsUseCase(repository)

    @Provides
    @Singleton
    fun provideRefreshFavoriteUseCase(
        repository: FavoriteProductsRepository
    ): RefreshFavoriteUseCase =
        RefreshFavoriteUseCase(
            repository
        )
}