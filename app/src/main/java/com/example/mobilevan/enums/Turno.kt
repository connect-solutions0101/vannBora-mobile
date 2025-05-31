package com.example.mobilevan.enums

import java.io.Serializable

enum class Turno : Serializable {
    PADRAO("P", "Padrão"),
    INTEGRAL("I", "Integral");

    private val valor: String;
    private val descricao: String;

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