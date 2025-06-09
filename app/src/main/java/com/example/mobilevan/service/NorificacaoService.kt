package com.example.mobilevan.service

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface NorificacaoService {

    @POST("notificacoes/enviar")
    suspend fun enviarNotificacao(
        @Header("Authorization") token: String,
        @Body mensagem: String
    ): Response<Void>

}