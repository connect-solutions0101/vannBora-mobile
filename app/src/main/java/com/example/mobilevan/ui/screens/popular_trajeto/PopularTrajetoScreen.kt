package com.example.mobilevan.ui.screens.popular_trajeto

import HomeTopBar
import SearchBarPrev
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobilevan.ui.components.CardAluno
import com.example.mobilevan.ui.theme.AzulVann

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopularTrajetoScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel()
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
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
                        containerColor = Color(0xFFFBBF24),
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
@Preview()
@Composable
fun CriarTrajetoPrev() {
    val vm = MainViewModel()
    PopularTrajetoScreen(viewModel = vm)
}