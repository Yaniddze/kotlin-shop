package com.example.kotlin_shop.di.modules

import com.example.kotlin_shop.domain.repositories.CartItemRepository
import com.example.kotlin_shop.domain.repositories.ViewedProductsRepository
import com.example.kotlin_shop.domain.usecases.AddCartItemUseCase
import com.example.kotlin_shop.domain.usecases.AddCartItemUseCaseImpl
import com.example.kotlin_shop.domain.usecases.AddViewedProductUseCase
import com.example.kotlin_shop.domain.usecases.AddViewedProductUseCaseImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DetailedPresenterUseCasesModule {

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