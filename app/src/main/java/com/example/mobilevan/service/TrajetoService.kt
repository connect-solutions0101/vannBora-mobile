package com.example.mobilevan.service

import com.example.mobilevan.service.dto.TrajetoDTO
import com.example.mobilevan.service.dto.TrajetoRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface TrajetoService {

    @GET("/trajetos/{id}")
    suspend fun getTrajetos( @Path("id") id: Int, @Header("Authorization") token: String ): Response<List<TrajetoDTO>>

    @GET("/trajetos/{id}/single")
    suspend fun getTrajeto( @Path("id") id: String, @Header("Authorization") token: String ): Response<TrajetoDTO>

    @POST("trajetos")
    suspend fun criarTrajeto(@Header("Authorization") token: String, @Body trajetoRequestDto: TrajetoRequestDto): Response<TrajetoDTO>

}