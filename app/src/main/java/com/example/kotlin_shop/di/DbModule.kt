package com.example.kotlin_shop.di

import android.content.Context
import com.example.kotlin_shop.App
import com.example.kotlin_shop.data.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule() {
    @Provides
    @Singleton
    fun provideContext(): Context = App.getContext()

    @Provides
    @Singleton
    fun provideDB(context: Context): AppDatabase = AppDatabase.getDatabase(context)
}