package com.example.kotlin_shop.di.components

import com.example.kotlin_shop.di.modules.*
import com.example.kotlin_shop.presentation.DetailedPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    DetailedPresenterUseCasesModule::class
])
interface DetailedPresenterComponent {

    fun inject(body: DetailedPresenter)

}