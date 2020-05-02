package com.example.kotlin_shop.di.modules

import com.example.kotlin_shop.data.entities.factories.CartItemFactory
import com.example.kotlin_shop.data.entities.factories.CartItemFactoryImpl
import com.example.kotlin_shop.domain.ViewedProductFactory
import com.example.kotlin_shop.domain.factories.ProductFactory
import com.example.kotlin_shop.domain.factories.ProductFactoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FactoriesModule {
    @Provides
    @Singleton
    fun provideProductFactory(): ProductFactory = ProductFactoryImpl()

    @Provides
    @Singleton
    fun provideCartItemFactory(): CartItemFactory = CartItemFactoryImpl()

    @Provides
    @Singleton
    fun provideViewedProductFactory(): ViewedProductFactory = ViewedProductFactory()
}