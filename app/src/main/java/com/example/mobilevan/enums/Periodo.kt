package com.example.mobilevan.enums

enum class Periodo {
    MANHA("M", "Manhã"),
    TARDE("T", "Tarde"),
    NOITE("N", "Noite");

    private val valor: String
    private val descricao: String

    constructor(valor: String, descricao: String) {
        this.valor = valor
        this.descricao = descricao
    }

    fun getValor(): String {
        return valor
    }

    fun getDescricao(): String {
        return descricao
    }
}