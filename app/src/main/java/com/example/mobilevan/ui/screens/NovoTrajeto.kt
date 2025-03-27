package com.example.mobilevan.ui.screens

import HomeTopBar
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mobilevan.ui.theme.AzulVann
import com.example.mobilevan.ui.theme.AmareloVann
import com.example.mobilevan.ui.theme.CinzaVann

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NovoTrajeto(navController: NavHostController) {
    var nomeTrajeto by remember { mutableStateOf("") }
    var periodoTrajeto by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
        HomeTopBar(
            title = "Olá Roberto",
            onNavigationIconClick = {navController.navigate("tela_inicial")},
            onActionIconClick = {},
            containerColor = AzulVann,
        )

        Column(modifier = Modifier.fillMaxSize().padding(horizontal = 56.dp, vertical = 0.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Card(
                colors = CardDefaults.cardColors(containerColor = AzulVann),
                modifier = Modifier.size(width = 300.dp, height = 329.dp)
            ) {
                Column(modifier = Modifier.fillMaxSize().padding(horizontal = 50.dp, vertical = 25.dp), verticalArrangement = Arrangement.spacedBy(13.dp, Alignment.CenterVertically)) {
                    Text(
                        text = "Novo Trajeto",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Nome do Trajeto",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    SuggestionChip(
                        colors = SuggestionChipDefaults.suggestionChipColors(containerColor = CinzaVann, labelColor = AzulVann),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {},
                        label = {
                            Text(
                                text = "Insira o nome",
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    )
                    Text(
                        text = "Perido do Trajeto",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    SuggestionChip(
                        colors = SuggestionChipDefaults.suggestionChipColors(containerColor = CinzaVann, labelColor = AzulVann),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {},
                        label = {
                            Text(
                                text = "Manhã, tarde, noite...",
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    )
                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = AmareloVann, contentColor = Color.Black),
                        shape = RoundedCornerShape(15.dp),
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {}
                    ) {
                        Text(text = "Adicionar")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNovoTrajeto() {
    NovoTrajeto(navController = rememberNavController())
}

