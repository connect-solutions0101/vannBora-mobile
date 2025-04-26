package com.example.mobilevan.ui.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobilevan.ui.screens.NovoTrajeto
import com.example.mobilevan.ui.screens.feature_login.TelaLogin
import com.example.mobilevan.ui.screens.feature_selecionar_trajeto.TrajetoScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.routes) {
        composable(Routes.Login.routes) { TelaLogin(navController) }
        composable(Routes.TelaInicial.routes) { TrajetoScreen(navController) }
        composable(Routes.NovoTrajeto.routes) { NovoTrajeto(navController) }
    }
}