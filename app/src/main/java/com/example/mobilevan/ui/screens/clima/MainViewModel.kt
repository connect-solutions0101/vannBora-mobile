package com.example.mobilevan.ui.screens.clima

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilevan.service.dto.WeatherResponse
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.mobilevan.config.RetrofitConfigHgbr
import com.example.mobilevan.store.TokenStore
import kotlinx.coroutines.flow.firstOrNull

class MainViewModel : ViewModel() {

    var nomeUsuario by mutableStateOf("")
    var weather by mutableStateOf<WeatherResponse?>(null)

    suspend fun fetchWeather(context: Context) {
        nomeUsuario = TokenStore.getUserName(context).firstOrNull() ?: ""
        try {
            val response = RetrofitConfigHgbr.api.getWeather()
            weather = response
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
