package com.example.kotlin_shop.di.modules

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
        repository: OrderRepository
    ): AddOrderUseCase =
        AddOrderUseCase(repository)

}