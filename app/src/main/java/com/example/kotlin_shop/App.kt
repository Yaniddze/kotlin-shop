package com.example.kotlin_shop

import android.app.Application
import com.example.kotlin_shop.data.AppDatabase
import com.example.kotlin_shop.di.AppComponent
import com.example.kotlin_shop.di.DaggerAppComponent

class App: Application() {

    companion object{
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .context(this)
            .build()
    }
}