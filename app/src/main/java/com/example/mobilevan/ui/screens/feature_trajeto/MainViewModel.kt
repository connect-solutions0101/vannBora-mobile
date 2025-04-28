package com.example.mobilevan.ui.screens.feature_trajeto

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mobilevan.config.RetrofitConfig
import com.example.mobilevan.service.TrajetoService
import com.example.mobilevan.service.dto.TrajetoDTO
import com.example.mobilevan.store.TokenStore
import kotlinx.coroutines.flow.firstOrNull

class MainViewModel: ViewModel(){

    var trajeto by mutableStateOf<TrajetoDTO?>(null)

    suspend fun onScreenLoad(context: Context, trajetoId: String) {
        val api = RetrofitConfig.instance.create(TrajetoService::class.java)
        val token = TokenStore.getToken(context).firstOrNull()
        val userId = TokenStore.getUserId(context).firstOrNull()

        if (token.isNullOrEmpty()  || userId == null) {
            println("Token or User ID is null")
            return
        }

        try {
            val response =  api.getTrajeto(trajetoId, "Bearer $token")

            if (response.raw().code == 200) {
                response.body()?.let { trajeto ->
                    println("Trajeto: $trajeto")
                    this.trajeto = trajeto
                }
            }  else {
                println("Error: ${response.raw().code}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}