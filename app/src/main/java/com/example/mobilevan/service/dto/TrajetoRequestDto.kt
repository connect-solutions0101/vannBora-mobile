package com.example.mobilevan.service.dto

import com.example.mobilevan.enums.Periodo

data class TrajetoRequestDto(
    val nome: String,
    val periodo: Periodo,
    val trajetoDependentes: List<TrajetoDependenteRequestDto>,
    val proprietarioServicoId: Int
)