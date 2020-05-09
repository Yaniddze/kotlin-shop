package com.example.kotlin_shop.di.modules

import com.example.kotlin_shop.data.AppDatabase
import com.example.kotlin_shop.data.dao.CartItemDao
import com.example.kotlin_shop.data.dao.FavoriteProductsDao
import com.example.kotlin_shop.data.dao.ProductsDao
import com.example.kotlin_shop.data.entities.factories.CartItemFactory
import com.example.kotlin_shop.data.repositories.*
import com.example.kotlin_shop.domain.FavoriteProductFactory
import com.example.kotlin_shop.domain.ViewedProductFactory
import com.example.kotlin_shop.domain.repositories.CartItemRepository
import com.example.kotlin_shop.domain.repositories.CatalogRepository
import com.example.kotlin_shop.domain.repositories.FavoriteProductsRepository
import com.example.kotlin_shop.domain.repositories.ViewedProductsRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @Provides
    @Singleton
    fun provideFavoritesDao(
        ab: AppDatabase
    ): FavoriteProductsDao = ab.favoriteDao()

    @Provides
    @Singleton
    fun provideFavoriteProductsRepository(
        dao: FavoriteProductsDao,
        factory: FavoriteProductFactory
    ): FavoriteProductsRepository = FavoriteProductsRepositoryImpl(dao, factory)

    @Provides
    @Singleton
    fun provideCartItemDao(db: AppDatabase): CartItemDao = db.cartItemDao()

    @Provides
    @Singleton
    fun provideCartItemRepository(
        dao: CartItemDao,
        cartItemFactory: CartItemFactory
    ): CartItemRepository = CartItemRepositoryImpl(cartItemFactory, dao)

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
        retrofit: Retrofit
    ): CatalogRepository = CatalogRepositoryRetrofit(
        retrofit.create(ProductsDao::class.java)
    )
}