package com.example.mobilevan.service.dto

import com.example.mobilevan.enums.Turno

data class DependenteDTO(
    val id: Int,
    val nome: String,
    val turno: Turno
)
