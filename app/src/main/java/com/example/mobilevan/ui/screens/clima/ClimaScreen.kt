package com.example.mobilevan.ui.screens.clima

import HomeTopBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mobilevan.R
import com.example.mobilevan.ui.theme.AzulVann

@Composable
fun ClimaScreen(
    navController: NavHostController,
    viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val weather = viewModel.weather.value

    LaunchedEffect(Unit) {
        viewModel.fetchWeather()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(238,240,242))
    ) {

        HomeTopBar(
            title = "Olá Roberto",
            onNavigationIconClick = {navController.navigate("login")},
            onActionIconClick = {},
            containerColor = AzulVann,
        )

        Spacer(modifier = Modifier.height(40.dp))

        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(24.dp)
        ) {
            Column {

                Text(
                    text = weather?.results?.city ?: "Carregando...",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(20.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${weather?.results?.temp ?: "--"}°",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text("Quinta-feira", fontSize = 14.sp)
                        Text("17/01/2020", fontSize = 14.sp)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "⛅",
                fontSize = 100.sp
            )
        }

        Spacer(modifier = Modifier.height(75.dp))


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFDCEAFF), RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Text("Próximos 3 dias", fontWeight = FontWeight.SemiBold)

            Spacer(modifier = Modifier.height(8.dp))

            weather?.results?.forecast?.take(4)?.forEach { forecast ->
                DiaClimaItem(
                    dia = "${forecast.weekday}, ${forecast.date}",
                    icone = climaEmoji(forecast.description),
                    temp = "${forecast.max}°C"
                )
            }
            DiaClimaItem("Sábado, 19 jan", "🌧", "23°C")
            DiaClimaItem("Domingo, 20 jan", "🌧", "19°C")
            DiaClimaItem("Segunda, 21 jan", "🌦", "21°C")
        }

        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 36.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            androidx.compose.material.IconButton(
                onClick = { },
                modifier = Modifier.padding(end = 16.dp)
            ) {
                androidx.compose.material.Icon(
                    painter = painterResource(id = R.drawable.cloud),
                    contentDescription = "Nuvem",
                    tint = Color.Black,
                    modifier = Modifier.size(50.dp)
                )
            }
            Spacer(modifier = Modifier.width(150.dp))
            androidx.compose.material.IconButton(onClick = { }) {
                androidx.compose.material.Icon(
                    painter = painterResource(id = R.drawable.x),
                    contentDescription = "Fechar",
                    tint = Color.Black,
                    modifier = Modifier.size(50.dp)
                )
            }
        }
    }
}

@Composable
fun DiaClimaItem(dia: String, icone: String, temp: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .background(Color(0xFF0D1F4E), RoundedCornerShape(20.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(dia, color = Color.White)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(icone, fontSize = 18.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Text(temp, color = Color.White)
        }
    }
}

fun climaEmoji(description: String): String {
    return when {
        description.contains("sol", ignoreCase = true) -> "☀"
        description.contains("nublado", ignoreCase = true) -> "☁"
        description.contains("chuva", ignoreCase = true) -> "🌧"
        description.contains("limpo", ignoreCase = true) -> "🌤"
        else -> "⛅"
    }
}

@Preview(showBackground = true)
@Composable
fun ClimaScreenPrev() {
    ClimaScreen(navController = rememberNavController())
}
