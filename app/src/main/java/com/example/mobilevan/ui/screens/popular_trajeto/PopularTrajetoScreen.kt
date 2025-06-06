package com.example.mobilevan.ui.screens.popular_trajeto

import HomeTopBar
import SearchBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.mobilevan.store.TokenStore
import com.example.mobilevan.ui.components.CardAluno
import com.example.mobilevan.ui.components.ModalSelecionarResponsavel
import com.example.mobilevan.ui.navigation.Routes
import com.example.mobilevan.ui.theme.AzulVann
import kotlinx.coroutines.launch
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopularTrajetoScreen(
    navController: NavHostController,
    trajetoId: String? = null,
    viewModel: MainViewModel = viewModel()
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val reorderState = rememberReorderableLazyListState(onMove = { from, to ->
        viewModel.moverAluno(from.index, to.index)
    })

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
                viewModel.aoConfirmarResponsavelAluno()
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
            SearchBar(
                searchText = viewModel.searchInput
                , onSearchTextChanged = {
                    viewModel.searchInput = it
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Disponíveis",
                color = AzulVann,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier
                    .heightIn(min = 0.dp, max = 400.dp)
                    .reorderable(reorderState)
                    .detectReorderAfterLongPress(reorderState),
                state = reorderState.listState,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (viewModel.listaAlunos.isEmpty()) {
                    item {
                        Text("Nenhum aluno disponível.")
                    }
                } else {
                    items(viewModel.listaAlunos.filter {
                        it.nome.contains(viewModel.searchInput, ignoreCase = true)
                    }, key = { it.id }) { aluno ->
                        ReorderableItem(reorderState, key = aluno.id) { isDragging ->
                            CardAluno(
                                isSelected = viewModel.isAlunoSelecionado(aluno),
                                nome = aluno.nome,
                                escola = aluno.escola.nome,
                                onClick = {
                                    viewModel.aoClicarCardAluno(
                                        aluno,
                                        !viewModel.isAlunoSelecionado(aluno)
                                    )
                                },
                                modifier = Modifier
                                    .background(if (isDragging) Color.LightGray else Color.White)
                            )
                        }
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