package com.example.mobilevan.telas

import HomeTopBar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import com.example.mobilevan.ui.theme.AzulVann
import com.example.mobilevan.ui.theme.AmareloVann
import com.example.mobilevan.ui.theme.CinzaVann

class Main : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                NovoTrajetoPrev()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NovoTrajetoPrev(){
    NovoTrajeto()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NovoTrajeto(modifier: Modifier = Modifier){
    var nomeTrajeto = remember{
        mutableStateOf("")
    }
    var periodoTrajeto = remember {
        mutableStateOf("")
    }

    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally){
        HomeTopBar(
            title = "Olá Roberto",
            onNavigationIconClick = {},
            onActionIconClick = {},
            containerColor = AzulVann,
        )
        Column(modifier = modifier.fillMaxSize().padding(horizontal = 56.dp, vertical = 0.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
            Card (
                colors = CardDefaults.cardColors(
                    containerColor = AzulVann,

                    ),
                modifier = Modifier.size(width = 300.dp, height = 329.dp)
            ){
                Column (modifier = modifier.fillMaxSize().padding(horizontal = 50.dp, vertical = 25.dp), verticalArrangement = Arrangement.spacedBy(13.dp, Alignment.CenterVertically)){
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
                        modifier = modifier.fillMaxWidth(),
                        onClick = {},
                        label = {
                            Text(
                                text = "Insira o nome",
                                textAlign = TextAlign.Center,
                                modifier=Modifier.fillMaxWidth()
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
                        colors = SuggestionChipDefaults.suggestionChipColors(
                            containerColor = CinzaVann,
                            labelColor = AzulVann
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = modifier.fillMaxWidth(),
                        onClick = {},
                        label = {
                            Text(
                                text = "Manhã, tarde, noite...",
                                textAlign = TextAlign.Center,
                                modifier=Modifier.fillMaxWidth()
                            )
                        }
                    )
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AmareloVann,
                            contentColor = Color.Black,
                        ),
                        shape = RoundedCornerShape(15.dp),

                        modifier = modifier.fillMaxWidth(),
                        onClick = {}
                    ) {
                        Text(text = "Adicionar")
                    }
                }
            }
        }
    }
}

