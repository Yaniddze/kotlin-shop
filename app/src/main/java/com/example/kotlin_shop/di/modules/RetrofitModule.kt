package com.example.kotlin_shop.di.modules

import com.example.kotlin_shop.data.RetrofitInstance
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RetrofitModule {
    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit = RetrofitInstance.retrofit

}