package com.example.mobilevan.service.dto

import com.example.mobilevan.enums.Periodo

data class TrajetoDTO (
    val id: Int,
    val nome: String,
    val periodo: Periodo,
    val trajetoDependentes: List<TrajetoDependenteDTO>
)