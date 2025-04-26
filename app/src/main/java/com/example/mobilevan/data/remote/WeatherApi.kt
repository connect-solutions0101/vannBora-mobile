package com.example.mobilevan.data.remote

import com.example.mobilevan.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun getWeather(
        @Query("woeid") cityId: String = "455827", // São Paulo
        @Query("key") apiKey: String = "f42b6bd8" // chave HGBrasil
    ): WeatherResponse
}
