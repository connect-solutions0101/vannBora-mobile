package com.example.mobilevan.service.dto

data class EmbarqueResponse (
    val id: Int,
    val dataHora: String,
    val tipo: String,
    val responsavelId: Int,
    val dependenteId: Int
)