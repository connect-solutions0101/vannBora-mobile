package com.example.mobilevan.service.dto

data class ResponsavelDTO(
    val id: Int,
    val nome: String,
    val telefone: String,
    val parentesco: String,
    val cpf: String,
    val endereco: EnderecoDTO
)
