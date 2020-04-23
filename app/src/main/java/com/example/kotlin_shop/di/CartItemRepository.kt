package com.example.kotlin_shop.di

import android.content.Context
import com.example.kotlin_shop.data.AppDatabase
import com.example.kotlin_shop.data.dao.CartItemDao
import com.example.kotlin_shop.data.entities.CartItemFactory
import com.example.kotlin_shop.data.repositories.CartItemRepositoryImpl
import com.example.kotlin_shop.domain.CartItemRepository
import com.example.kotlin_shop.domain.ProductFactory
import com.example.kotlin_shop.presenter.CartPresenter
import com.example.kotlin_shop.presenter.DetailedPresenter
import dagger.Component
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
    fun provideProductFactory(): ProductFactory = ProductFactory()

    @Provides
    @Singleton
    fun provideCartItemFactory(): CartItemFactory = CartItemFactory()

    @Provides
    @Singleton
    fun provideCartItemRepository(
        dao: CartItemDao,
        productFactory: ProductFactory,
        cartItemFactory: CartItemFactory
    ): CartItemRepository = CartItemRepositoryImpl(productFactory, cartItemFactory, dao)
}



@Singleton
@Component(modules = [
    DbModule::class,
    CartItemRepositoryModule::class
])
interface CartItemRepositoryComponent{
    fun getContext(): Context
    fun getDB(): AppDatabase
    fun getCartItemDao(): CartItemDao
    fun getCartItemFactory(): CartItemFactory
    fun getProductFactory(): ProductFactory
    fun getCartItemRepository(): CartItemRepository
    fun inject(body: CartPresenter)
    fun inject(body: DetailedPresenter)
}