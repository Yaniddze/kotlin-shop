package com.example.kotlin_shop.di.components

import com.example.kotlin_shop.di.modules.CartItemRepositoryModule
import com.example.kotlin_shop.di.modules.CartPresenterUseCasesModule
import com.example.kotlin_shop.di.modules.DbModule
import com.example.kotlin_shop.di.modules.FactoriesModule
import com.example.kotlin_shop.presenter.CartPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    FactoriesModule::class,
    DbModule::class,
    CartItemRepositoryModule::class,
    CartPresenterUseCasesModule::class
])
interface CartPresenterComponent {



    fun inject(body: CartPresenter)

}