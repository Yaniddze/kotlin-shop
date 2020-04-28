package com.example.kotlin_shop.di.components

import com.example.kotlin_shop.di.modules.*
import com.example.kotlin_shop.presentation.CatalogPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CatalogPresenterUseCasesModule::class
    ]
)
interface CatalogPresenterComponent {
    fun inject(body: CatalogPresenter)
}