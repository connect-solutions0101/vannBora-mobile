package com.example.mobilevan.ui.screens.feature_selecionar_trajeto

import HomeTopBar
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mobilevan.ui.theme.AzulVann

@Composable
fun TrajetoScreen(navController: NavHostController) {
    LaunchedEffect(Unit) {
        Log.d("TrajetoScreen", "Componente iniciado")
    }

    val trajetos = List(0) { "Trajeto 1\nManhã\n28 Alunos" }
    var selectedTrajeto by remember { mutableStateOf(-1) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        HomeTopBar(
            title = "Olá Roberto",
            onNavigationIconClick = {navController.navigate("login")},
            onActionIconClick = {},
            containerColor = AzulVann,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(bottom = 53.dp, top = 90.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if(trajetos.isEmpty()) {
                CoreListagemVaziaTrajetos()
            } else {
                CoreListagemTrajetos(trajetos, selectedTrajeto) { index ->
                    selectedTrajeto = index
                }
            }
        }


        Button(
            onClick = { /* TODO: Adicionar novo trajeto */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFFC107),
                contentColor = Color.Black,
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .width(225.dp)
                .height(55.dp)
        ) {
            Text(text = "+ Novo Trajeto", color = Color.Black, fontSize = 16.sp)
        }
    }
}

@Composable
fun TrajetoCard(trajeto: String, isSelected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .width(308.dp)
            .height(94.dp)
            .padding(vertical = 8.dp)
            .background(Color(0xFF001F4D), shape = RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = trajeto, color = Color.White, modifier = Modifier.weight(1f))
        Checkbox(
            checked = isSelected,
            onCheckedChange = { onClick() },
            colors = CheckboxDefaults.colors(checkedColor = Color.White, checkmarkColor = Color.White, uncheckedColor = Color.White)
        )
    }
}

@Composable
fun CoreListagemVaziaTrajetos() {
    Text(
        text = "Parece que vc\n" +
                "ainda não tem\n" +
                "nenhum trajeto",
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    )

    Spacer(modifier = Modifier.height(80.dp))

    Text(
        text = buildAnnotatedString {
            withStyle(
                style = androidx.compose.ui.text.SpanStyle(
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("Vannbora")
            }
            withStyle(style = androidx.compose.ui.text.SpanStyle(fontWeight = FontWeight.Bold)) {
                append(" criar um\n")
            }
            withStyle(
                style = androidx.compose.ui.text.SpanStyle(
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("Trajeto?")
            }
        },
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun CoreListagemTrajetos(
    trajetos: List<String>,
    selectedTrajeto: Int,
    onTrajetoSelected: (Int) -> Unit
) {
    Spacer(modifier = Modifier.height(20.dp))

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
    }

    Spacer(modifier = Modifier.height(20.dp))


    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 40.dp)
    ) {
        items(trajetos.size) { index ->
            TrajetoCard(trajetos[index], index == selectedTrajeto) {
                onTrajetoSelected(index)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTrajetoScreen() {
    TrajetoScreen(navController = rememberNavController())
}
