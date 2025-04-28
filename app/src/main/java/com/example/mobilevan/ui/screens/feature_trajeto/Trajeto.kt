package com.example.mobilevan.ui.screens.feature_trajeto

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mobilevan.R
import com.example.mobilevan.ui.navigation.Routes

@Preview(showBackground = true)
@Composable
fun Trajeto(
    navController: NavHostController = rememberNavController(),
    trajetoId: String? = null,
    viewModel: MainViewModel = viewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        if (trajetoId == null) {
            navController.navigate(Routes.SelecionarTrajeto.route)
        } else {
            viewModel.onScreenLoad(context, trajetoId)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { viewModel.trajeto?.let { Text(it.nome, color = Color.White, fontSize = 20.sp) } },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.seta),
                            contentDescription = "Voltar",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.pause),
                            contentDescription = "Pausar",
                            tint = Color.White
                        )
                    }
                },
                backgroundColor = Color(0xFF001F4D)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top = 100.dp, start = 24.dp, end = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Próximo aluno:",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Nome do aluno",
                    fontSize = 18.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(Color(0xFFFBBF24), shape = RoundedCornerShape(12.dp)),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFBBF24)),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    ) {
                        Text(
                            text = "Mais detalhes",
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(60.dp))
            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(120.dp)
                    .background(Color(0xFF001F4D), shape = RoundedCornerShape(12.dp))
                    .align(Alignment.CenterHorizontally)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.check),
                    contentDescription = "Confirmar",
                    tint = Color.White,
                    modifier = Modifier.size(80.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 36.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = { }, modifier = Modifier.padding(end = 16.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.cloud),
                        contentDescription = "Nuvem",
                        tint = Color.Black,
                        modifier = Modifier.size(50.dp)
                    )
                }
                Spacer(modifier = Modifier.width(150.dp))
                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(id = R.drawable.x),
                        contentDescription = "Fechar",
                        tint = Color.Black,
                        modifier = Modifier.size(50.dp)
                    )
                }
            }
        }
    }
}
