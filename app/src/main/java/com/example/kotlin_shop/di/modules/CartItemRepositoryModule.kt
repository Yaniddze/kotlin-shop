package com.example.kotlin_shop.di.modules

import com.example.kotlin_shop.data.AppDatabase
import com.example.kotlin_shop.data.dao.CartItemDao
import com.example.kotlin_shop.data.entities.factories.CartItemFactory
import com.example.kotlin_shop.data.repositories.CartItemRepositoryImpl
import com.example.kotlin_shop.domain.CartItemRepository
import com.example.kotlin_shop.domain.factories.ProductFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CartItemRepositoryModule{
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
}
