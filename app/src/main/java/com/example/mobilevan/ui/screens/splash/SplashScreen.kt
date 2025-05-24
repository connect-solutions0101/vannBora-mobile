package com.example.mobilevan.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobilevan.R
import com.example.mobilevan.ui.navigation.Routes
import com.example.mobilevan.ui.theme.AzulVann

@Composable
fun SplashScreen(
    navController: NavHostController,
    viewModel: MainViewModel = viewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.onScreenLoad(context)
    }

    viewModel.isTokenValid?.let { isValid ->
        LaunchedEffect(isValid) {
            navController.navigate(
                if (isValid) Routes.SelecionarTrajeto.route
                else Routes.Login.route
            ) {
                popUpTo(Routes.Splash.route) { inclusive = true }
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
    ) { innerPadding ->
         Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
             Image(
                 painter = painterResource(id = R.drawable.logobus),
                 contentDescription = "Ícone de ônibus",
                 modifier = Modifier
                     .width(120.dp)
                     .height(120.dp)
             )
             Image(
                 painter = painterResource(id = R.drawable.logotipo),
                 contentDescription = "Logo VANN BORA",
                 modifier = Modifier
                     .width(250.dp)
                     .height(60.dp)
             )
             CircularProgressIndicator(
                color = AzulVann,
                modifier = Modifier
                    .padding(16.dp)
                    .width(50.dp)
                    .height(50.dp)
             )
        }
    }
}

@Preview
@Composable
private fun prev() {
    SplashScreen(
        navController = NavHostController(LocalContext.current),
        viewModel = MainViewModel()
    )
}