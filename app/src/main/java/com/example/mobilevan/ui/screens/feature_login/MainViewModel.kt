package com.example.mobilevan.ui.screens.feature_login

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mobilevan.config.RetrofitConfig
import com.example.mobilevan.service.AuthService
import com.example.mobilevan.service.request.LoginRequest
import com.example.mobilevan.store.TokenStore

class MainViewModel : ViewModel() {

    var email by mutableStateOf("")

    var password by mutableStateOf("")

    suspend fun onLoginClick(context: Context): Boolean {
        val success = login(context)
        if (success) {
            println("Login concluído com sucesso!")
            val token = TokenStore.getToken(context)
            println("Token: $token")
        } else {
            println("Falha no login.")
        }
        return success
    }

    private suspend fun login(context: Context): Boolean {
        val authApi = RetrofitConfig.instance.create(AuthService::class.java)

        val request = LoginRequest(email, password)

        return try {
            val response = authApi.login(request)
            if (response.isSuccessful) {
                response.body()?.let {
                    TokenStore.saveToken(context, it.token)
                    println("Login realizado")
                    return true
                }
            }
            false
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}