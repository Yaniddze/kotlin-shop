package com.example.kotlin_shop.di.modules

import android.content.Context
import com.example.kotlin_shop.App
import com.example.kotlin_shop.data.AppDatabase
import com.example.kotlin_shop.data.dao.CartItemDao
import com.example.kotlin_shop.data.entities.factories.CartItemFactory
import com.example.kotlin_shop.data.entities.factories.CartItemFactoryImpl
import com.example.kotlin_shop.data.repositories.CartItemRepositoryImpl
import com.example.kotlin_shop.domain.factories.ProductFactory
import com.example.kotlin_shop.domain.factories.ProductFactoryImpl
import com.example.kotlin_shop.domain.repositories.CartItemRepository
import com.example.kotlin_shop.domain.usecases.DeleteCartItemUseCase
import com.example.kotlin_shop.domain.usecases.DeleteCartItemUseCaseImpl
import com.example.kotlin_shop.domain.usecases.GetCartItemsUseCase
import com.example.kotlin_shop.domain.usecases.GetCartItemsUseCaseImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CartPresenterUseCasesModule {

    @Provides
    @Singleton
    fun provideContext(): Context = App.getContext()

    @Provides
    @Singleton
    fun provideDataBase(context: Context): AppDatabase = AppDatabase.getDatabase(context)

    @Provides
    @Singleton
    fun provideProductFactory(): ProductFactory = ProductFactoryImpl()

    @Provides
    @Singleton
    fun provideCartItemFactory(): CartItemFactory = CartItemFactoryImpl()

    @Provides
    @Singleton
    fun provideCartItemDao(db: AppDatabase): CartItemDao = db.cartItemDao()

    @Provides
    @Singleton
    fun provideCartItemRepository(
        cartItemFactory: CartItemFactory,
        productFactory: ProductFactory,
        dao: CartItemDao
    ): CartItemRepository = CartItemRepositoryImpl(productFactory, cartItemFactory, dao)

    @Provides
    @Singleton
    fun getGetCartItemUseCase(repository: CartItemRepository): GetCartItemsUseCase =
        GetCartItemsUseCaseImpl(repository)

    @Provides
    @Singleton
    fun getDeleteCartItemUseCase(repository: CartItemRepository): DeleteCartItemUseCase =
        DeleteCartItemUseCaseImpl(repository)

}