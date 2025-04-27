package com.example.mobilevan.service.dto

data class EnderecoDTO(
    val id: Int,
    val logradouro: String,
    val numero: String,
    val bairro: String,
    val cidade: String,
    val cep: String,
    val pontoReferencia: String?,
)
