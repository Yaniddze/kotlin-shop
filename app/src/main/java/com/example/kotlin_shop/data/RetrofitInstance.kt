package com.example.kotlin_shop.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val retrofit: Retrofit = Retrofit.Builder()
//        .baseUrl("http://207.254.71.167:9191/")
        .baseUrl("http://192.168.43.223:9191/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}