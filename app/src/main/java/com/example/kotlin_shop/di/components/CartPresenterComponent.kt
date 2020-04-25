package com.example.kotlin_shop.di.components

import com.example.kotlin_shop.di.modules.*
import com.example.kotlin_shop.presentation.CartPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    FactoriesModule::class,
    ContextModule::class,
    DbModule::class,
    CartItemRepositoryModule::class,
    CartPresenterUseCasesModule::class
])
interface CartPresenterComponent {

    fun inject(body: CartPresenter)

}