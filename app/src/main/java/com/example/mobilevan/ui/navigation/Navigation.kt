package com.example.mobilevan.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mobilevan.ui.screens.criar_trajeto.CriarTrajetoScreen
import com.example.mobilevan.ui.screens.listar_trajeto.ListagemTrajetosScreen
import com.example.mobilevan.ui.screens.clima.ClimaScreen
import com.example.mobilevan.ui.screens.login.LoginScreen
import com.example.mobilevan.ui.screens.modificar_trajeto.ModificarTrajetoScreen
import com.example.mobilevan.ui.screens.popular_trajeto.PopularTrajetoScreen
import com.example.mobilevan.ui.screens.splash.SplashScreen
import com.example.mobilevan.ui.screens.trajeto.TrajetoScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Splash.toString()) {
        composable(Routes.Splash.route) { SplashScreen(navController) }
        composable(Routes.Login.route) { LoginScreen(navController) }
        composable(Routes.NovoTrajeto.route) { CriarTrajetoScreen(navController) }
        composable(Routes.SelecionarTrajeto.route) { ListagemTrajetosScreen(navController) }
        composable(route = Routes.Trajeto.route, arguments = listOf(
            navArgument("trajetoId") { type = NavType.StringType }
        )) {backStackEntry ->
            val trajetoId = backStackEntry.arguments?.getString("trajetoId")
            TrajetoScreen(navController, trajetoId)
        }
        composable(route = Routes.PopularTrajeto.route, arguments = listOf(
            navArgument("trajetoId") { type = NavType.StringType }
        )) {backStackEntry ->
            val trajetoId = backStackEntry.arguments?.getString("trajetoId")
            PopularTrajetoScreen(navController, trajetoId)
        }
        composable(route = Routes.ModificarTrajeto.route, arguments = listOf(
            navArgument("trajetoId") { type = NavType.StringType }
        )) {backStackEntry ->
            val trajetoId = backStackEntry.arguments?.getString("trajetoId")
            ModificarTrajetoScreen(navController, trajetoId)
        }
        composable(Routes.ClimaScreen.route) { ClimaScreen(navController)}
    }
}