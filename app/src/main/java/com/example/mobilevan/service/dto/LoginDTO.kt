package com.example.mobilevan.service.dto

import com.example.mobilevan.enums.ProprietarioServicoRole

data class LoginDTO(
    val id: Int,
    val nome: String,
    val email: String,
    val cpf: String,
    val role: ProprietarioServicoRole,
    val token: String
)
