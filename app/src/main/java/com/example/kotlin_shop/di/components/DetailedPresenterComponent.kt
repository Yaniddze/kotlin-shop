package com.example.kotlin_shop.di.components

import com.example.kotlin_shop.di.modules.CartItemRepositoryModule
import com.example.kotlin_shop.di.modules.DbModule
import com.example.kotlin_shop.di.modules.DetailedPresenterUseCasesModule
import com.example.kotlin_shop.di.modules.FactoriesModule
import com.example.kotlin_shop.presenter.DetailedPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    DbModule::class,
    FactoriesModule::class,
    CartItemRepositoryModule::class,
    DetailedPresenterUseCasesModule::class
])
interface DetailedPresenterComponent {
    fun inject(body: DetailedPresenter)
}