package com.example.kotlin_shop.di.modules

import com.example.kotlin_shop.domain.repositories.CartItemRepository
import com.example.kotlin_shop.domain.usecases.DeleteCartItemUseCase
import com.example.kotlin_shop.domain.usecases.GetCartItemsUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CartPresenterUseCasesModule {

    @Provides
    @Singleton
    fun getGetCartItemUseCase(repository: CartItemRepository): GetCartItemsUseCase =
        GetCartItemsUseCase(repository)

    @Provides
    @Singleton
    fun getDeleteCartItemUseCase(repository: CartItemRepository): DeleteCartItemUseCase =
        DeleteCartItemUseCase(repository)

}