package com.example.mobilevan.service.dto

import com.example.mobilevan.enums.Turno
import java.io.Serializable

data class DependenteDTO(
    val id: Int,
    val nome: String,
    val turno: Turno,
    val escola: EscolaDTO,
    val responsaveis: List<ResponsavelDependenteDTO>,
    var ordem: Int? = null,
) : Serializable
