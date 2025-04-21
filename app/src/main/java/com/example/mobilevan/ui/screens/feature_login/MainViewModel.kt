package com.example.mobilevan.ui.screens.feature_login

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobilevan.config.RetrofitConfig
import com.example.mobilevan.service.AuthService
import com.example.mobilevan.service.request.LoginRequest
import com.example.mobilevan.store.TokenStore
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var email by mutableStateOf("")

    var password by mutableStateOf("")

    fun onLoginClick(context: Context){
        System.out.println("Login Clicked")
        System.out.println("Email: $email")
        System.out.println("Password: $password")

        login(context)
    }

    fun login(context: Context) = viewModelScope.launch {
        val authApi = RetrofitConfig
            .instance
            .create(AuthService::class.java)

        val request = LoginRequest(
            email = email,
            password = password
        )

        try{
            val response = authApi.login(request)

            System.out.println("Response: $response")
            System.out.println("Body: ${response.body()}")

            if(response.isSuccessful){
                val loginDTO = response.body()
                System.out.println("Login realizado com sucesso!")
                System.out.println("Token: ${loginDTO?.token}")
                TokenStore.saveToken(context, response.body()!!.token)
            }

        } catch (e: Exception){
            e.printStackTrace()
        }
    }


}