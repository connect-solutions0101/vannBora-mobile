package com.example.mobilevan.ui.screens.feature_clima

import HomeTopBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
fun ClimaScreen(navController: NavHostController) {
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

                Text( modifier = Modifier.align(Alignment.CenterHorizontally), text = "São Paulo", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(20.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("23°", fontSize = 36.sp, fontWeight = FontWeight.Bold)
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
            Text("Próximos 4 dias", fontWeight = FontWeight.SemiBold)

            Spacer(modifier = Modifier.height(8.dp))

            DiaClimaItem("Sexta, 18 jan", "☀", "27°C")
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

@Preview(showBackground = true)
@Composable
fun ClimaScreenPrev() {
    ClimaScreen(navController = rememberNavController())
}
