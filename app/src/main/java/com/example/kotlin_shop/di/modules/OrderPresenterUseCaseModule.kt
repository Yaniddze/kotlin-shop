package com.example.kotlin_shop.di.modules

import com.example.kotlin_shop.domain.repositories.OrderRepository
import com.example.kotlin_shop.domain.usecases.order.GetAllOrdersUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class OrderPresenterUseCaseModule {
    @Provides
    @Singleton
    fun provideGetAllOrders(repository: OrderRepository) =
        GetAllOrdersUseCase(repository)
}