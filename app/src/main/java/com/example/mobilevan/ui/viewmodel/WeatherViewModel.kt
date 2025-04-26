package com.example.mobilevan.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilevan.data.model.WeatherResponse
import com.example.mobilevan.data.remote.RetrofitInstance
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class WeatherViewModel : ViewModel() {

    private val _weather = mutableStateOf<WeatherResponse?>(null)
    val weather: State<WeatherResponse?> = _weather

    fun fetchWeather() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getWeather()
                _weather.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
