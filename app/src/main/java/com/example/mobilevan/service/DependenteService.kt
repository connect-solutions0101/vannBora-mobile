package com.example.mobilevan.service

import com.example.mobilevan.service.dto.DependenteDTO
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface DependenteService {

    @GET("/dependentes/proprietarioServico/{proprietarioServicoId}")
    suspend fun getDependentes(
        @Path("proprietarioServicoId") proprietarioServicoId: String,
        @Header("Authorization") token: String
    ): retrofit2.Response<List<DependenteDTO>>
}