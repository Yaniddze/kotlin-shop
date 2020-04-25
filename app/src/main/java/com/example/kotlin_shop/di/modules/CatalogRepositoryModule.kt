package com.example.kotlin_shop.di.modules

import com.example.kotlin_shop.data.repositories.CatalogRepositoryImpl
import com.example.kotlin_shop.domain.factories.ProductFactory
import com.example.kotlin_shop.domain.repositories.CatalogRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CatalogRepositoryModule {

    @Provides
    @Singleton
    fun provideCatalogRepository(
        factory: ProductFactory
    ): CatalogRepository = CatalogRepositoryImpl(factory)

}