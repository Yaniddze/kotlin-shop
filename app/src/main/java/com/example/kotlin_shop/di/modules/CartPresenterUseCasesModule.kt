package com.example.kotlin_shop.di.modules

import com.example.kotlin_shop.domain.repositories.CartItemRepository
import com.example.kotlin_shop.domain.usecases.cart.DeleteCartItemUseCase
import com.example.kotlin_shop.domain.usecases.cart.UpdateCartItemUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CartPresenterUseCasesModule {

    @Provides
    @Singleton
    fun getDeleteCartItemUseCase(repository: CartItemRepository): DeleteCartItemUseCase =
        DeleteCartItemUseCase(
            repository
        )

    @Provides
    @Singleton
    fun getUpdateCartItemUseCase(repository: CartItemRepository): UpdateCartItemUseCase =
        UpdateCartItemUseCase(
            repository
        )
}