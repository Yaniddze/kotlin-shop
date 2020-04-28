package com.example.kotlin_shop.di.modules

import android.content.Context
import com.example.kotlin_shop.App
import com.example.kotlin_shop.data.AppDatabase
import com.example.kotlin_shop.data.RetrofitInstance
import com.example.kotlin_shop.data.dao.CartItemDao
import com.example.kotlin_shop.data.dao.ProductsDao
import com.example.kotlin_shop.data.entities.factories.CartItemFactory
import com.example.kotlin_shop.data.entities.factories.CartItemFactoryImpl
import com.example.kotlin_shop.data.repositories.CartItemRepositoryImpl
import com.example.kotlin_shop.data.repositories.CatalogRepositoryRetrofit
import com.example.kotlin_shop.data.repositories.ViewedProductsRepositoryImpl
import com.example.kotlin_shop.domain.factories.ProductFactory
import com.example.kotlin_shop.domain.factories.ProductFactoryImpl
import com.example.kotlin_shop.domain.repositories.CartItemRepository
import com.example.kotlin_shop.domain.repositories.CatalogRepository
import com.example.kotlin_shop.domain.repositories.ViewedProductsRepository
import com.example.kotlin_shop.domain.usecases.AddCartItemUseCase
import com.example.kotlin_shop.domain.usecases.AddCartItemUseCaseImpl
import com.example.kotlin_shop.domain.usecases.AddViewedProductUseCase
import com.example.kotlin_shop.domain.usecases.AddViewedProductUseCaseImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class DetailedPresenterUseCasesModule {

    @Provides
    @Singleton
    fun provideContext(): Context = App.getContext()

    @Provides
    @Singleton
    fun provideDB(context: Context): AppDatabase = AppDatabase.getDatabase(context)

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
        dao: CartItemDao,
        productFactory: ProductFactory,
        cartItemFactory: CartItemFactory
    ): CartItemRepository = CartItemRepositoryImpl(productFactory, cartItemFactory, dao)

    @Provides
    @Singleton
    fun provideViewedProductsRepository(
        factory: ProductFactory,
        context: Context
    ): ViewedProductsRepository = ViewedProductsRepositoryImpl(
        context.getSharedPreferences("my_viewed_products", Context.MODE_PRIVATE),
        factory
    )

    @Provides
    @Singleton
    fun provideAddCartItemUseCase(repository: CartItemRepository): AddCartItemUseCase =
        AddCartItemUseCaseImpl(repository)

    @Provides
    @Singleton
    fun provideAddViewedProductUseCases(
        repository: ViewedProductsRepository
    ): AddViewedProductUseCase = AddViewedProductUseCaseImpl(repository)

}