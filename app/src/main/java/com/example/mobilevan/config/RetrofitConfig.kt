package com.example.mobilevan.config

import com.example.mobilevan.service.WeatherApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfig {
    val instance = Retrofit
        .Builder()
        .baseUrl("http://192.168.15.5:8080")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

object RetrofitConfigHgbr {
    private const val BASE_URL = "https://api.hgbrasil.com/"
    val api: WeatherApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}