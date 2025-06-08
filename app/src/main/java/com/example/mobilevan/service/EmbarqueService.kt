package com.example.mobilevan.service

import com.example.mobilevan.service.dto.EmbarqueResponse
import com.example.mobilevan.service.request.EmbarqueRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface EmbarqueService {

    @POST("registros-embarques-desembarques")
    suspend fun registrar(
        @Header("Authorization") token: String,
        @Body embarqueRequest: EmbarqueRequest
    ): Response<EmbarqueResponse>

}