package com.example.mobilevan.ui.screens.feature_criar_trajeto

import HomeTopBar
import SearchBarPrev
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobilevan.ui.components.CardAluno
import com.example.mobilevan.ui.theme.AmareloVann
import com.example.mobilevan.ui.theme.AzulVann

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CriarTrajeto(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel()
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            HomeTopBar(
                title = "Olá ${viewModel.nomeUsuario}",
                onNavigationIconClick = {},
                onActionIconClick = {},
                containerColor = AzulVann
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 56.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            SearchBarPrev()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Disponíveis",
                color = AzulVann,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .weight(1f, fill = false)
                    .heightIn(min = 0.dp, max = 400.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (viewModel.listaAlunos.isEmpty()) {
                    Text("Nenhum aluno disponível.")
                } else {
                    viewModel.listaAlunos.forEach { alunoPair ->
                        CardAluno(
                            nome = alunoPair.first,
                            escola = alunoPair.second,
                            onClick = { viewModel.aoClicarCardAluno(alunoPair) }
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AmareloVann,
                        contentColor = Color.Black,
                    ),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    onClick = viewModel::onNovoTrajetoClick,
                ) {
                    Text(text = "Novo Trajeto", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CriarTrajetoPrev() {
    val vm = MainViewModel()
    CriarTrajeto(viewModel = vm)
}