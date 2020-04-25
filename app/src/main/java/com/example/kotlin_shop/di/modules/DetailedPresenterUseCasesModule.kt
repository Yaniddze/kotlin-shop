package com.example.kotlin_shop.di.modules

import com.example.kotlin_shop.domain.repositories.CartItemRepository
import com.example.kotlin_shop.domain.usecases.AddCartItemUseCase
import com.example.kotlin_shop.domain.usecases.AddCartItemUseCaseImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DetailedPresenterUseCasesModule {

    @Provides
    @Singleton
    fun provideAddCartItemUseCase(repository: CartItemRepository): AddCartItemUseCase =
        AddCartItemUseCaseImpl(repository)

}