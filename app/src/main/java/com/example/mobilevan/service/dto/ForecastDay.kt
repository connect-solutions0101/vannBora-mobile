package com.example.mobilevan.service.dto
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDay(
    val weekday: String,
    val date: String,
    val max: Int,
    val min: Int,
    val description: String
)
