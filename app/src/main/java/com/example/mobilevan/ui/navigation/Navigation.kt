package com.example.mobilevan.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mobilevan.ui.screens.feature_novo_trajeto.NovoTrajeto
import com.example.mobilevan.ui.screens.feature_selecionar_trajeto.SelecionarTrajeto
import com.example.mobilevan.ui.screens.feature_clima.ClimaScreen
import com.example.mobilevan.ui.screens.feature_login.TelaLogin
import com.example.mobilevan.ui.screens.feature_trajeto.Trajeto

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.toString()) {
        composable(Routes.Login.route) { TelaLogin(navController) }
        composable(Routes.NovoTrajeto.route) { NovoTrajeto(navController) }
        composable(Routes.SelecionarTrajeto.route) { SelecionarTrajeto(navController) }
        composable(route = Routes.Trajeto.route, arguments = listOf(
            navArgument("trajetoId") { type = NavType.StringType }
        )) {backStackEntry ->
            val trajetoId = backStackEntry.arguments?.getString("trajetoId")
            Trajeto(navController, trajetoId)
        }
        composable(Routes.ClimaScreen.route) { ClimaScreen(navController)}
    }
}