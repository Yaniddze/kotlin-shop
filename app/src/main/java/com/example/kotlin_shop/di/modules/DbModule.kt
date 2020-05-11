package com.example.kotlin_shop.di.modules

import android.content.Context
import com.example.kotlin_shop.data.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    @Provides
    @Singleton
    fun provideDB(context: Context): AppDatabase = AppDatabase.getDatabase(context)

}