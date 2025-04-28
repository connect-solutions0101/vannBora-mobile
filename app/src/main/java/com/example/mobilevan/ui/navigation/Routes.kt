package com.example.mobilevan.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes(val route: String) {

    @Serializable
    data object Login : Routes("Login")

    @Serializable
    data object SelecionarTrajeto : Routes("SelecionarTrajeto")

    @Serializable
    data object NovoTrajeto : Routes("NovoTrajeto")

    @Serializable
    data object Trajeto : Routes("Trajeto/{trajetoId}")

    @Serializable
    data object ClimaScreen : Routes("ClimaScreen")
}