package com.example.mobilevan.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.*
import com.example.mobilevan.ui.screens.NovoTrajeto
import com.example.mobilevan.ui.screens.TelaLogin
import com.example.mobilevan.ui.screens.TrajetosVaziosScreen

@Preview(showBackground = true)
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable(Routes.Login.routes) { TelaLogin(navController) }
        composable(Routes.TelaInicial.routes) { TrajetosVaziosScreen(navController) }
        composable(Routes.NovoTrajeto.routes) { NovoTrajeto(navController) }
    }
}
