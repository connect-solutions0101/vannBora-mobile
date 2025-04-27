package com.example.mobilevan.service.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResults(
    val temp: Int,
    val city: String,
    @SerialName("date") val date: String,
    @SerialName("forecast") val forecast: List<ForecastDay>
)
