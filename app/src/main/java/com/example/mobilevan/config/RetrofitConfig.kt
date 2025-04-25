package com.example.mobilevan.config

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfig {
    val instance = Retrofit
        .Builder()
        .baseUrl("http://10.0.2.2:8080")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}