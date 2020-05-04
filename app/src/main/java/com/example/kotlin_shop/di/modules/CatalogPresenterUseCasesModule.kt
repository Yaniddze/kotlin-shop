package com.example.kotlin_shop.di.modules

import com.example.kotlin_shop.domain.repositories.CatalogRepository
import com.example.kotlin_shop.domain.repositories.ViewedProductsRepository
import com.example.kotlin_shop.domain.usecases.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CatalogPresenterUseCasesModule {

    @Provides
    @Singleton
    fun provideGetViewedProductsUseCase(
        repository: ViewedProductsRepository
    ): GetViewedProductsUseCase = GetViewedProductsUseCase(repository)



    @Provides
    @Singleton
    fun provideMainGetCatalogUseCase(
        repository: CatalogRepository
    ): GetCatalogUseCase = GetCatalogUseCase(repository)

    @Provides
    @Singleton
    fun provideMainAddCatalogItemUseCase(
        repository: CatalogRepository
    ): AddCatalogItemUseCase = AddCatalogItemUseCase(repository)

    @Provides
    @Singleton
    fun provideGetHintsUseCase(
        repository: CatalogRepository
    ): GetHintsUseCase = GetHintsUseCase(repository)
}