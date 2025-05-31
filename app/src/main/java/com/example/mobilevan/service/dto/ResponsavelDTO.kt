package com.example.mobilevan.service.dto

import java.io.Serializable

data class ResponsavelDTO(
    val id: Int,
    val nome: String,
    val telefone: String,
    val parentesco: String,
    val cpf: String,
    val endereco: EnderecoDTO
) : Serializable
