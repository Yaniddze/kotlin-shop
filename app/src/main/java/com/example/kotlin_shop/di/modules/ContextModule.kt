package com.example.kotlin_shop.di.modules

import android.content.Context
import com.example.kotlin_shop.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule {

    @Provides
    @Singleton
    fun provideContext(): Context = App.getContext()

}