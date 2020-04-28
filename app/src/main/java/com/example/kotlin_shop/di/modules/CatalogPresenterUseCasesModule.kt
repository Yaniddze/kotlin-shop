package com.example.kotlin_shop.di.modules

import android.content.Context
import com.example.kotlin_shop.App
import com.example.kotlin_shop.data.RetrofitInstance
import com.example.kotlin_shop.data.dao.ProductsDao
import com.example.kotlin_shop.data.repositories.CatalogRepositoryImpl
import com.example.kotlin_shop.data.repositories.CatalogRepositoryRetrofit
import com.example.kotlin_shop.data.repositories.ViewedProductsRepositoryImpl
import com.example.kotlin_shop.domain.factories.ProductFactory
import com.example.kotlin_shop.domain.factories.ProductFactoryImpl
import com.example.kotlin_shop.domain.repositories.CatalogRepository
import com.example.kotlin_shop.domain.repositories.ViewedProductsRepository
import com.example.kotlin_shop.domain.usecases.*
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class CatalogPresenterUseCasesModule {

    @Provides
    @Singleton
    fun provideContext(): Context = App.getContext()

    @Provides
    @Singleton
    fun provideProductFactory(): ProductFactory = ProductFactoryImpl()

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit = RetrofitInstance.retrofit

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
    fun provideGetViewedProductsUseCase(
        repository: ViewedProductsRepository
    ): GetViewedProductsUseCase = GetViewedProductsUseCaseImpl(repository)


    @Provides
    @Singleton
    fun provideMainCatalogRepository(
        retrofit: Retrofit,
        factory: ProductFactory
    ): CatalogRepositoryRetrofit = CatalogRepositoryRetrofit(
        retrofit.create(ProductsDao::class.java),
        factory
    )

    @Provides
    @Singleton
    fun provideSubCatalogRepository(
        factory: ProductFactory
    ): CatalogRepositoryImpl = CatalogRepositoryImpl(factory)

    @Provides
    @Named("mainCatalogGetter")
    fun provideMainGetCatalogUseCase(
        repository: CatalogRepositoryRetrofit
    ): GetCatalogUseCase = GetCatalogUseCaseImpl(repository)

    @Provides
    @Named("mainCatalogAdder")
    fun provideMainAddCatalogItemUseCase(
        repository: CatalogRepositoryRetrofit
    ): AddCatalogItemUseCase = AddCatalogItemUseCaseImpl(repository)



    @Provides
    @Named("subCatalogGetter")
    fun provideSubGetCatalogUseCase(
        repository: CatalogRepositoryImpl
    ): GetCatalogUseCase = GetCatalogUseCaseImpl(repository)

    @Provides
    @Named("subCatalogAdder")
    fun provideSubAddCatalogItemUseCase(
        repository: CatalogRepositoryImpl
    ): AddCatalogItemUseCase = AddCatalogItemUseCaseImpl(repository)
}