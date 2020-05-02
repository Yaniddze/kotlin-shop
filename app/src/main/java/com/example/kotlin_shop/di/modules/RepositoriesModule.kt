package com.example.kotlin_shop.di.modules

import android.content.Context
import com.example.kotlin_shop.data.AppDatabase
import com.example.kotlin_shop.data.dao.CartItemDao
import com.example.kotlin_shop.data.dao.ProductsDao
import com.example.kotlin_shop.data.entities.factories.CartItemFactory
import com.example.kotlin_shop.data.repositories.CartItemRepositoryImpl
import com.example.kotlin_shop.data.repositories.CatalogRepositoryRetrofit
import com.example.kotlin_shop.data.repositories.ViewedProductsRepositoryRoom
import com.example.kotlin_shop.data.repositories.ViewedProductsRepositoryShared
import com.example.kotlin_shop.domain.ViewedProductFactory
import com.example.kotlin_shop.domain.factories.ProductFactory
import com.example.kotlin_shop.domain.repositories.CartItemRepository
import com.example.kotlin_shop.domain.repositories.CatalogRepository
import com.example.kotlin_shop.domain.repositories.ViewedProductsRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RepositoriesModule {
    @Provides
    @Singleton
    fun provideCartItemDao(db: AppDatabase): CartItemDao = db.cartItemDao()

    @Provides
    @Singleton
    fun provideCartItemRepository(
        dao: CartItemDao,
        productFactory: ProductFactory,
        cartItemFactory: CartItemFactory
    ): CartItemRepository = CartItemRepositoryImpl(productFactory, cartItemFactory, dao)

    @Provides
    @Singleton
    fun provideViewedProductsRepository(
        factory: ViewedProductFactory,
        db: AppDatabase
    ): ViewedProductsRepository = ViewedProductsRepositoryRoom(
        db.viewedProductsDao(),
        factory
    )


    @Provides
    @Singleton
    fun provideCatalogRepository(
        retrofit: Retrofit,
        factory: ProductFactory
    ): CatalogRepository = CatalogRepositoryRetrofit(
        retrofit.create(ProductsDao::class.java),
        factory
    )
}