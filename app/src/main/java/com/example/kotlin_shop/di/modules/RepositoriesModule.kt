package com.example.kotlin_shop.di.modules

import com.example.kotlin_shop.data.AppDatabase
import com.example.kotlin_shop.data.dao.*
import com.example.kotlin_shop.data.entities.factories.CartItemFactory
import com.example.kotlin_shop.data.repositories.*
import com.example.kotlin_shop.domain.FavoriteProductFactory
import com.example.kotlin_shop.domain.ViewedProductFactory
import com.example.kotlin_shop.domain.repositories.*
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

    @Provides
    @Singleton
    fun provideOrderDao(
        retrofit: Retrofit
    ): OrderDao = retrofit.create(OrderDao::class.java)

    @Provides
    @Singleton
    fun provideOrderRepository(
        dao: OrderDao
    ): OrderRepository = OrderRepositoryImpl(dao)

    @Provides
    @Singleton
    fun provideGenreRepository(
        retrofit: Retrofit
    ): GenreRepository = GenreRepositoryImpl(retrofit.create(GenresDao::class.java))
}