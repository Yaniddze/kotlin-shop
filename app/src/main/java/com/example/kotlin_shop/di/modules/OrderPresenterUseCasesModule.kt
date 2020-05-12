package com.example.kotlin_shop.di.modules

import com.example.kotlin_shop.domain.repositories.CartItemRepository
import com.example.kotlin_shop.domain.repositories.OrderRepository
import com.example.kotlin_shop.domain.usecases.order.AddOrderUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class OrderPresenterUseCasesModule {

    @Provides
    @Singleton
    fun provideAddOrderUseCase(
        orderRepository: OrderRepository,
        cartItemRepository: CartItemRepository
    ): AddOrderUseCase =
        AddOrderUseCase(orderRepository, cartItemRepository)

}