package com.example.mobilevan.enums

enum class ProprietarioServicoRole {
    ADMIN("ADMIN"),
    USER("USER");

    private val value: String

    constructor(value: String) {
        this.value = value
    }
}