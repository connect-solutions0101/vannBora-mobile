package com.example.mobilevan.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobilevan.ui.navigation.Routes
import com.example.mobilevan.ui.screens.NovoTrajeto
import com.example.mobilevan.ui.screens.feature_selecionar_trajeto.TrajetoScreen
import com.example.mobilevan.ui.screens.feature_clima.ClimaScreen
import com.example.mobilevan.ui.screens.feature_login.TelaLogin

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.toString()) {
        composable(Routes.Login.toString()) { TelaLogin(navController) }
        composable(Routes.NovoTrajeto.toString()) { NovoTrajeto(navController) }
//        composable(Routes.SelecionarTrajeto.toString()) { TrajetoScreen(navController) }
//        composable(Routes.Trajetos.toString()) { TrajetoAlunoScreen(navController)}
        composable(Routes.ClimaScreen.toString()) { ClimaScreen(navController)}
    }
}