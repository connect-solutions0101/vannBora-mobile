package com.example.mobilevan.service

import com.example.mobilevan.service.dto.LoginDTO
import com.example.mobilevan.service.request.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthService {

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginDTO>

}