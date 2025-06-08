package com.example.mobilevan.service.request

data class EmbarqueRequest (
    val dataHora: String,
    val tipo: String,
    val responsavelId: Int,
    val dependenteId: Int
)