package com.example.mobilevan.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    @SerialName("results") val results: WeatherResults
)

@Serializable
data class WeatherResults(
    val temp: Int,
    val city: String,
    @SerialName("date") val date: String,
    @SerialName("forecast") val forecast: List<ForecastDay>
)

@Serializable
data class ForecastDay(
    val weekday: String,
    val date: String,
    val max: Int,
    val min: Int,
    val description: String
)
