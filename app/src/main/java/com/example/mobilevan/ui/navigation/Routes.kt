package com.example.mobilevan.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes{

    @Serializable
    data object Login : Routes()

    @Serializable
    data object SelecionarTrajeto : Routes()

    @Serializable
    data object NovoTrajeto : Routes()

    @Serializable
    data object Trajetos : Routes()

    @Serializable
    data object ClimaScreen : Routes()

}