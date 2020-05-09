package com.example.kotlin_shop.di.modules

import com.example.kotlin_shop.data.entities.factories.CartItemFactory
import com.example.kotlin_shop.domain.FavoriteProductFactory
import com.example.kotlin_shop.domain.ViewedProductFactory
import com.example.kotlin_shop.domain.factories.ProductFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FactoriesModule {

    @Provides
    @Singleton
    fun provideFavoriteProductsFactory(): FavoriteProductFactory = FavoriteProductFactory()

    @Provides
    @Singleton
    fun provideProductFactory(): ProductFactory = ProductFactory()

    @Provides
    @Singleton
    fun provideCartItemFactory(): CartItemFactory = CartItemFactory()

    @Provides
    @Singleton
    fun provideViewedProductFactory(): ViewedProductFactory = ViewedProductFactory()
}