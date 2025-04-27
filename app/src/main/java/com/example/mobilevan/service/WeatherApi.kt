package com.example.mobilevan.service

import com.example.mobilevan.service.dto.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun getWeather(
        @Query("woeid") cityId: String = "455827", // São Paulo
        @Query("key") apiKey: String = "f42b6bd8" // chave HGBrasil
    ): WeatherResponse
}
