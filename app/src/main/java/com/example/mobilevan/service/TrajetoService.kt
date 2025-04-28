package com.example.mobilevan.service

import com.example.mobilevan.service.dto.TrajetoDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface TrajetoService {

    @GET("/trajetos/{id}")
    suspend fun getTrajetos( @Path("id") id: Int, @Header("Authorization") token: String ): Response<List<TrajetoDTO>>

    @GET("/trajetos/{id}/single")
    suspend fun getTrajeto( @Path("id") id: String, @Header("Authorization") token: String ): Response<TrajetoDTO>
}