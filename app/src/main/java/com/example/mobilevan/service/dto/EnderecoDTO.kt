package com.example.mobilevan.service.dto

import java.io.Serializable

data class EnderecoDTO(
    val id: Int,
    val logradouro: String,
    val numero: String,
    val bairro: String,
    val cidade: String,
    val cep: String,
    val pontoReferencia: String?,
) : Serializable
