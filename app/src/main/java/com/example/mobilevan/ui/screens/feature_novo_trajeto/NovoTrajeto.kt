package com.example.mobilevan.ui.screens.feature_novo_trajeto

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

    Scaffold(
        topBar = {
            HomeTopBar(
                title = "Olá Roberto",
                onNavigationIconClick = { navController.navigate("tela_inicial") },
                onActionIconClick = {},
                containerColor = AzulVann,
            )
        },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                colors = CardDefaults.cardColors(containerColor = AzulVann),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
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
                        colors = SuggestionChipDefaults.suggestionChipColors(
                            containerColor = CinzaVann,
                            labelColor = AzulVann
                        ),
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
                        text = "Período do Trajeto",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    SuggestionChip(
                        colors = SuggestionChipDefaults.suggestionChipColors(
                            containerColor = CinzaVann,
                            labelColor = AzulVann
                        ),
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

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AmareloVann,
                            contentColor = Color.Black
                        ),
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
