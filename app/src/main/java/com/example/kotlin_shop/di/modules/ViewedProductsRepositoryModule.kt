package com.example.kotlin_shop.di.modules

import android.content.Context
import com.example.kotlin_shop.data.repositories.ViewedProductsRepositoryImpl
import com.example.kotlin_shop.domain.factories.ProductFactory
import com.example.kotlin_shop.domain.repositories.ViewedProductsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewedProductsRepositoryModule {

    @Provides
    @Singleton
    fun provideViewedProductsRepository(
        factory: ProductFactory,
        context: Context
    ): ViewedProductsRepository = ViewedProductsRepositoryImpl(
        context.getSharedPreferences("my_viewed_products", Context.MODE_PRIVATE),
        factory
    )

}