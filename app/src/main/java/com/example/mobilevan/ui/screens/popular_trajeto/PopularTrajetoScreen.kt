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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mobilevan.service.request.DependenteResponsavelRequest
import com.example.mobilevan.store.TokenStore
import com.example.mobilevan.ui.components.CardAluno
import com.example.mobilevan.ui.components.ModalSelecionarResponsavel
import com.example.mobilevan.ui.navigation.Routes
import com.example.mobilevan.ui.theme.AzulVann
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopularTrajetoScreen(
    navController: NavHostController,
    trajetoId: String? = null,
    viewModel: MainViewModel = viewModel()
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.onScreenLoad(context, trajetoId)
    }

    LaunchedEffect(viewModel.trajetoPopulado) {
        if (viewModel.trajetoPopulado) {
            navController.navigate(Routes.SelecionarTrajeto.route)
        }
    }

    if(viewModel.showResponsavelDialog){
        ModalSelecionarResponsavel(
            responsaveis = viewModel.listaResponsaveis,
            onConfirmarClick = {
                viewModel.showResponsavelDialog = false
                viewModel.listaAlunosParaSalvar.add(
                    DependenteResponsavelRequest(
                        idDependente = viewModel.dependenteId!!,
                        idResponsavel = viewModel.responsavelSelecionado!!.id
                    )
                )
           },
            onResponsavelClick = {
                if(viewModel.dependenteId == null){
                    return@ModalSelecionarResponsavel
                }
                viewModel.responsavelSelecionado = it
            },
            isSelected = { it == viewModel.responsavelSelecionado }
        )
    }

    Scaffold(
        modifier = Modifier
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
                .background(Color(0xFFF5F5F5))
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
                    viewModel.listaAlunos.forEach { aluno ->
                        CardAluno(
                            isSelected = viewModel.isAlunoSelecionado(aluno),
                            nome = aluno.nome,
                            escola = aluno.escola.nome,
                            onClick = { viewModel.aoClicarCardAluno(
                                aluno,
                                !viewModel.isAlunoSelecionado(aluno)
                            ) }
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
                    onClick = {
                        coroutineScope.launch {
                            viewModel.onNovoTrajetoClick(
                                trajetoId = trajetoId
                            )
                        }
                    }
                ) {
                    Text(text = "Inserir alunos no Trajeto", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
            }
        }
    }
}
@Preview()
@Composable
fun CriarTrajetoPrev() {
    val vm = MainViewModel()
    PopularTrajetoScreen(viewModel = vm, navController = rememberNavController())
}