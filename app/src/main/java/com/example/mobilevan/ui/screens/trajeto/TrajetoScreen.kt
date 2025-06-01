package com.example.mobilevan.ui.screens.trajeto

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mobilevan.R
import com.example.mobilevan.service.dto.DependenteDTO
import com.example.mobilevan.ui.components.ModalInformacoesAdicionais
import com.example.mobilevan.ui.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Preview()
@Composable
fun TrajetoScreen(
    navController: NavHostController = rememberNavController(),
    trajetoId: String? = null,
    viewModel: MainViewModel = viewModel()
) {
    val context = LocalContext.current

    LaunchedEffect(viewModel.trajetoFinalizado) {
        if (viewModel.trajetoFinalizado) {
            navController.navigate(Routes.SelecionarTrajeto.route)
        }
    }

    LaunchedEffect(Unit) {
        val listaTrajetoDependentes = navController.previousBackStackEntry
            ?.savedStateHandle
            ?.get<List<DependenteDTO>>("trajetoDependentes") ?: emptyList()

        viewModel.trajetoDependentes = listaTrajetoDependentes

        if (trajetoId == null) {
            navController.navigate(Routes.SelecionarTrajeto.route)
        } else {
            viewModel.onScreenLoad(context, trajetoId)
        }
    }

    ModalInformacoesAdicionais(
        nome = viewModel.dependenteAtual?.nome ?: "Carregando...",
        telefone = viewModel.dependenteAtual?.responsaveis?.get(0)?.responsavel?.telefone ?: "Carregando...",
        escola = viewModel.dependenteAtual?.escola?.nome ?: "Carregando...",
        responsavel = viewModel.dependenteAtual?.responsaveis?.get(0)?.responsavel?.nome ?: "Carregando...",
        parentesco = viewModel.dependenteAtual?.responsaveis?.get(0)?.responsavel?.parentesco ?: "Carregando...",
        rua = viewModel.dependenteAtual?.responsaveis?.get(0)?.responsavel?.endereco?.logradouro ?: "Carregando...",
        numero = viewModel.dependenteAtual?.responsaveis?.get(0)?.responsavel?.endereco?.numero ?: "Carregando...",
        visible = viewModel.modalInformacoesAdicionaisVisible,
        onDismissRequest = { viewModel.onDismissRequest() },
        onConfirmClick = { viewModel.onDismissRequest() }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { viewModel.trajeto?.let { Text(it.nome, color = Color.White, fontSize = 20.sp) } },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Routes.SelecionarTrajeto.route)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.seta),
                            contentDescription = "Voltar",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF001F4D),
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF5F5F5))
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
                viewModel.dependenteAtual?.nome?.let {
                    Text(
                        text = it,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = {
                         viewModel.onMaisInformacoesClick()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(Color(0xFFFBBF24), shape = RoundedCornerShape(12.dp)),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFBBF24),
                        contentColor = Color.Black,
                    ),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    ) {
                        Text(
                            text = "Mais informações",
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(60.dp))
            IconButton(
                onClick = {
                     viewModel.onConfirmClick()
                },
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
                IconButton(onClick = {
                    navController.navigate(Routes.ClimaScreen.route)
                }, modifier = Modifier.padding(end = 16.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.cloud),
                        contentDescription = "Nuvem",
                        tint = Color.Black,
                        modifier = Modifier.size(50.dp)
                    )
                }
                Spacer(modifier = Modifier.width(150.dp))
                IconButton(onClick = {
                    navController.navigate(Routes.SelecionarTrajeto.route)
                }) {
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
