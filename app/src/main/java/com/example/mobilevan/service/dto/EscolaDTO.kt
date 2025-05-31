package com.example.mobilevan.service.dto

import java.io.Serializable

data class EscolaDTO (
    val id: Int,
    val nome: String,
    val nomeResponsavel: String,
    val telefone: String,
    val telefoneResponsavel: String
) : Serializable
