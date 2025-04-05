package com.example.mobilevan.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes(val routes: String){

    object Login : Routes("login")

    object TelaInicial : Routes("tela_inicial")

    object NovoTrajeto : Routes("novo_trajeto")
}