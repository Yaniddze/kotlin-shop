package com.example.kotlin_shop

import android.app.Application
import com.example.kotlin_shop.data.AppDatabase
import com.example.kotlin_shop.di.components.DaggerAppComponent

class App: Application() {

    companion object{
        private lateinit var INSTANCE: Application

        fun getContext() = INSTANCE.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
//        DaggerAppComponent.builder()
//            .context(this)
//            .build()
    }

}