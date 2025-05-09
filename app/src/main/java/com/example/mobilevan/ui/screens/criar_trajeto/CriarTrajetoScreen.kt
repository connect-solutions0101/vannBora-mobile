package com.example.mobilevan.ui.screens.criar_trajeto

import HomeTopBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mobilevan.enums.Periodo
import com.example.mobilevan.ui.components.ComboBox
import com.example.mobilevan.ui.navigation.Routes
import com.example.mobilevan.ui.theme.AzulVann
import com.example.mobilevan.ui.theme.CinzaVann
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun CriarTrajetoScreen(
    navController: NavHostController,
    viewModel: MainViewModel = viewModel()
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(viewModel.trajetoCriado) {
        if (viewModel.trajetoCriado) {
            navController.navigate(Routes.SelecionarTrajeto.route)
        }
    }

    Scaffold(
        topBar = {
            HomeTopBar(
                title = "Olá Roberto",
                onNavigationIconClick = { navController.navigate("tela_inicial") },
                onActionIconClick = {},
                containerColor = AzulVann,
            )
        },
        containerColor = Color.White,
        modifier = Modifier.background(Color(0xFFF5F5F5))
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
                    .height(420.dp)
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
                    OutlinedTextField(
                        value = viewModel.nomeTrajeto,
                        onValueChange = { viewModel.nomeTrajeto = it },
                        shape = RoundedCornerShape(8.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = CinzaVann,
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent
                        ),
                        textStyle = TextStyle(textAlign = TextAlign.Center),
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    Text(
                        text = "Período do Trajeto",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 16.dp, bottom = 4.dp)
                    )
                    ComboBox(
                        options = Periodo.entries,
                        selectedOption = viewModel.periodoTrajeto,
                        optionToString = { it?.getDescricao() ?: "" },
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFBBF24),
                            contentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(15.dp),
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            coroutineScope.launch {
                                viewModel.onAdicionarNovoTrajetoClick(
                                    context = context
                                )
                            }
                        }
                    ) {
                        Text(text = "Adicionar")
                    }
                }
            }
        }
    }
}
@Preview()
@Composable
fun PreviewNovoTrajeto() {
    CriarTrajetoScreen(navController = rememberNavController())
}
