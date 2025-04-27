package com.example.mobilevan.ui.screens.feature_clima

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilevan.service.dto.WeatherResponse
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.example.mobilevan.config.RetrofitConfigHgbr

class WeatherViewModel : ViewModel() {

    private val _weather = mutableStateOf<WeatherResponse?>(null)
    val weather: State<WeatherResponse?> = _weather

    fun fetchWeather() {
        viewModelScope.launch {
            try {
                val response = RetrofitConfigHgbr.api.getWeather()
                _weather.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
