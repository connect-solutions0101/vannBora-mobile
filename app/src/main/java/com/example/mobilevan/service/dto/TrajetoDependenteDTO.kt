package com.example.mobilevan.service.dto

data class TrajetoDependenteDTO(
    val id: Int,
    val responsavelDependente: ResponsavelDependenteDTO,
    val ordem: Int? = null
)
