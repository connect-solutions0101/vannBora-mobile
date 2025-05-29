package com.example.mobilevan.ui.screens.modificar_trajeto

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.mobilevan.store.TokenStore
import com.example.mobilevan.ui.components.CardAluno
import com.example.mobilevan.ui.navigation.Routes
import com.example.mobilevan.ui.screens.modificar_trajeto.MainViewModel
import com.example.mobilevan.ui.theme.AzulVann
import kotlinx.coroutines.launch

@Composable
fun ModificarTrajetoScreen (
    navController: NavHostController,
    trajetoId: String? = null,
    viewModel: MainViewModel = viewModel()
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.onScreenLoad(context, trajetoId)
    }

    LaunchedEffect(viewModel.iniciarTrajeto) {
        if (viewModel.iniciarTrajeto) {
            navController.currentBackStackEntry
                ?.savedStateHandle
                ?.set("trajetoDependentes", viewModel.listaAlunosTrajetoAtual)
            navController.navigate("Trajeto/1")
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        topBar = {
            HomeTopBar(
                title = "Olá ${viewModel.nomeUsuario}",
                onNavigationIconClick = {
                    navController.navigate(Routes.SelecionarTrajeto.route)
                },
                onActionIconClick = {
                    coroutineScope.launch {
                        TokenStore.clear(context)
                        navController.navigate(Routes.Login.route)
                    }
                },
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
                if (viewModel.listaAlunosTrajeto.isEmpty()) {
                    Text("Nenhum aluno disponível.")
                } else {
                    viewModel.listaAlunosTrajeto.forEach { aluno ->
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
                        viewModel.onIniciarTrajetoClick()
                    }
                ) {
                    Text(text = "Iniciar Trajeto", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
            }
        }
    }
}