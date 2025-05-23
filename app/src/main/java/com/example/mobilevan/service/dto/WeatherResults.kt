package com.example.mobilevan.service.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResults(
    val temp: Int,
    val city: String,
    @SerialName("date") val date: String,
    @SerialName("description") val description: String,
    @SerialName("condition_slug") val condition_slug: String,
    @SerialName("forecast") val forecast: List<ForecastDay>,
)
