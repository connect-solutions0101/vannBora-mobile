package com.example.mobilevan.ui.screens.feature_selecionar_trajeto

import HomeTopBar
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mobilevan.service.dto.TrajetoDTO
import com.example.mobilevan.ui.theme.AzulVann

@Composable
fun TrajetoScreen(
    navController: NavHostController,
    viewModel: MainViewModel = viewModel()
) {
    val context = LocalContext.current
    var selectedTrajeto by remember { mutableStateOf(-1) }

    LaunchedEffect(Unit) {
        Log.d("TrajetoScreen", "Componente iniciado")
        viewModel.onScreenLoad(context)
    }

    Scaffold(
        topBar = {
            HomeTopBar(
                title = "Olá Roberto",
                onNavigationIconClick = {navController.navigate("login")},
                onActionIconClick = {},
                containerColor = AzulVann,
            ) },
        modifier = Modifier.background(Color(0xFFF5F5F5)),
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { /* TODO: Adicionar novo trajeto */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFC107),
                        contentColor = Color.Black,
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .padding(16.dp)
                        .width(225.dp)
                        .height(55.dp)
                ) {
                    Text(text = "+ Novo Trajeto", color = Color.Black, fontSize = 16.sp)
                }
            }

        }
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .fillMaxHeight(0.8f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if(viewModel.trajetos.isEmpty()) {
                CoreListagemVaziaTrajetos()
            } else {
                CoreListagemTrajetos(viewModel.trajetos, selectedTrajeto) { index ->
                    selectedTrajeto = index
                }
            }
        }
    }
}

@Composable
fun CoreListagemVaziaTrajetos() {
    Text(
        text = "Parece que você ainda não tem nenhum trajeto!",
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
    )

    Spacer(modifier = Modifier.height(80.dp))

    Text(
        text = buildAnnotatedString {
            withStyle(
                style = androidx.compose.ui.text.SpanStyle(
                    color = AzulVann,
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
                    color = AzulVann,
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
    trajetos: List<TrajetoDTO>,
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

@Composable
fun TrajetoCard(trajeto: TrajetoDTO, isSelected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .width(308.dp)
            .height(94.dp)
            .background(Color(0xFF001F4D), shape = RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(16.dp, 10.5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "Trajeto ${trajeto.nome}",
                color = Color.White,
                fontSize = 16.sp
            )
            Text(
                text = "Período ${trajeto.periodo.getDescricao()}",
                color = Color.White,
                fontSize = 16.sp
            )
            Text(
                text = "${trajeto.trajetoDependentes.size} Alunos",
                color = Color.White,
                fontSize = 16.sp
            )
        }
        Checkbox(
            checked = isSelected,
            onCheckedChange = { onClick() },
            colors = CheckboxDefaults.colors(checkedColor = Color.White, checkmarkColor = Color.White, uncheckedColor = Color.White)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTrajetoScreen() {
    TrajetoScreen(navController = rememberNavController())
}
