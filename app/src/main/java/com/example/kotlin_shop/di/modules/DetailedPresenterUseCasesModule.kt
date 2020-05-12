package com.example.kotlin_shop.di.modules

import com.example.kotlin_shop.domain.repositories.CartItemRepository
import com.example.kotlin_shop.domain.repositories.CatalogRepository
import com.example.kotlin_shop.domain.repositories.FavoriteProductsRepository
import com.example.kotlin_shop.domain.repositories.ViewedProductsRepository
import com.example.kotlin_shop.domain.usecases.*
import com.example.kotlin_shop.domain.usecases.cart.AddCartItemUseCase
import com.example.kotlin_shop.domain.usecases.viewed.AddViewedProductUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DetailedPresenterUseCasesModule {

    @Provides
    @Singleton
    fun provideAddCartItemUseCase(repository: CartItemRepository): AddCartItemUseCase =
        AddCartItemUseCase(
            repository
        )

    @Provides
    @Singleton
    fun provideAddViewedProductUseCases(
        repository: ViewedProductsRepository
    ): AddViewedProductUseCase =
        AddViewedProductUseCase(
            repository
        )

    @Provides
    @Singleton
    fun provideGetProductByIdUseCase(
        catalogRepository: CatalogRepository,
        favoriteRepository: FavoriteProductsRepository
    ): GetProductByIdUseCase = GetProductByIdUseCase(catalogRepository, favoriteRepository)

}