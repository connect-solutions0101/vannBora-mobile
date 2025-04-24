package com.example.mobilevan.ui.screens.feature_selecionar_trajeto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

class SelecionarTrajetoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SelecionarTrajetoScreen()
        }
    }
}

@Composable
fun SelecionarTrajetoScreen() {
    val trajetos = List(4) { "Trajeto ${it + 1}\nManhã\n28 Alunos" }
    var selectedTrajeto by remember { mutableStateOf(-1) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBar()

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Selecione um",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "trajeto",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0D21A1)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                trajetos.forEachIndexed { index, trajeto ->
                    TrajetoCard(trajeto, index == selectedTrajeto) {
                        selectedTrajeto = index
                    }
                }
            }
        }

        Button(
            onClick = { /* TODO: Implementar ação */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFC107)),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .width(225.dp)
                .height(55.dp)
        ) {
            Text(text = "+ Novo Trajeto", color = Color.Black, fontSize = 16.sp)
        }
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF001F4D), shape = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /* TODO: Home */ }) {
            Icon(imageVector = Icons.Filled.Home, contentDescription = "Home", tint = Color.White)
        }
        Text(
            text = "Olá, Roberto",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        IconButton(onClick = { /* TODO: Perfil */ }) {
            Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "Perfil", tint = Color.White)
        }
    }
}

@Composable
fun TrajetoCard(trajeto: String, isSelected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .width(308.dp)
            .height(94.dp)
            .background(Color(0xFF001F4D), shape = RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = trajeto,
            color = Color.White,
            modifier = Modifier.weight(1f)
        )
        Checkbox(
            checked = isSelected,
            onCheckedChange = { onClick() },
            colors = CheckboxDefaults.colors(
                checkedColor = Color.White,
                checkmarkColor = Color.Black,
                uncheckedColor = Color.White
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSelecionarTrajetoScreen() {
    SelecionarTrajetoScreen()
}
