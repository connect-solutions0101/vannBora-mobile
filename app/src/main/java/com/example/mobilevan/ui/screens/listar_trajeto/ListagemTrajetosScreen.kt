package com.example.mobilevan.ui.screens.listar_trajeto

import HomeTopBar
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.mobilevan.service.dto.TrajetoDTO
import com.example.mobilevan.store.TokenStore
import com.example.mobilevan.ui.components.ModalSelecionarTrajeto
import com.example.mobilevan.ui.navigation.Routes
import com.example.mobilevan.ui.theme.AzulVann
import kotlinx.coroutines.launch

@Composable
fun ListagemTrajetosScreen(
    navController: NavHostController,
    viewModel: MainViewModel = viewModel()
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.onScreenLoad(context)
    }

    ModalSelecionarTrajeto(
        showDialog = viewModel.showTrajetoDialog,
        onDismiss = { viewModel.showTrajetoDialog = false },
        title = "O que deseja fazer?",
        labelButton1 = "Iniciar",
        onSelecionarOpcao1 = {
            viewModel.showTrajetoDialog = false
            viewModel.trajetoSelecionado?.id?.let {
                navController.navigate("ModificarTrajeto/$it")
            }
        },
        labelButton2 = "Editar",
        onSelecionarOpcao2 = {
            viewModel.showTrajetoDialog = false
            viewModel.trajetoSelecionado?.id?.let {
                navController.navigate("PopularTrajeto/$it")
            }
        }
    )

    Scaffold(
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
                containerColor = AzulVann,
            ) },
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF5F5F5))
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        navController.navigate(Routes.NovoTrajeto.route)
                    },
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
                .background(Color(0xFFF5F5F5))
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if(viewModel.trajetos.isEmpty()) {
                CoreListagemVaziaTrajetos()
            } else {
                CoreListagemTrajetos(viewModel.trajetos) { index ->
                    viewModel.onTrajetoClick(viewModel.trajetos[index])
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
                style = SpanStyle(
                    color = AzulVann,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("Vannbora")
            }
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(" criar um\n")
            }
            withStyle(
                style = SpanStyle(
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
    onTrajetoSelected: (Int) -> Unit
) {
    Spacer(modifier = Modifier.height(20.dp))

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Selecione um",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = AzulVann,
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
            TrajetoCard(trajetos[index]) {
                onTrajetoSelected(index)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun TrajetoCard(trajeto: TrajetoDTO, onClick: () -> Unit) {
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
    }
}

@Preview
@Composable
private fun prev() {
    ListagemTrajetosScreen(
        navController = NavHostController(LocalContext.current),
        viewModel = MainViewModel()
    )
}