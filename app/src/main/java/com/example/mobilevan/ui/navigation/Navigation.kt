package com.example.mobilevan.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobilevan.ui.screens.NovoTrajeto
import com.example.mobilevan.ui.screens.TelaLogin
import com.example.mobilevan.ui.screens.TrajetosVaziosScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.routes) {
        composable(Routes.Login.routes) { TelaLogin(navController) }
        composable(Routes.TelaInicial.routes) { TrajetosVaziosScreen(navController) }
        composable(Routes.NovoTrajeto.routes) { NovoTrajeto(navController) }
    }
}
