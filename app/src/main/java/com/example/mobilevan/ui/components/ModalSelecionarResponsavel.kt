package com.example.mobilevan.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.mobilevan.service.dto.EnderecoDTO
import com.example.mobilevan.service.dto.ResponsavelDTO
import com.example.mobilevan.ui.theme.AzulVann

@Composable
fun ModalSelecionarResponsavel(
    responsaveis: List<ResponsavelDTO>,
    onConfirmarClick: () -> Unit,
    onResponsavelClick: (ResponsavelDTO) -> Unit,
    isSelected:(ResponsavelDTO) -> Boolean = { false }
){
    Dialog(onDismissRequest = onConfirmarClick) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp
        ) {
            Column(modifier = Modifier.padding(24.dp)) {

                Text(
                    text = "Selecione o responsável",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    items(responsaveis) { responsavel ->
                        CardResponsavel(
                            isSelected = isSelected(responsavel),
                            nome = responsavel.nome,
                            telefone = responsavel.telefone,
                            onClick = {
                                onResponsavelClick(responsavel)
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onConfirmarClick,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AzulVann,
                        contentColor = Color.White
                    )
                ) {
                    Text("Confirmar")
                }
            }
        }
    }
}

@Preview
@Composable
private fun ModalSelecionarResponsavelPreview() {
    val responsaveis = listOf(
        ResponsavelDTO(
            id = 1,
            nome = "Roberto",
            telefone = "123456789",
            parentesco = "Pai",
            cpf = "12345678900",
            endereco = EnderecoDTO(
                id = 1,
                logradouro = "Rua A",
                numero = "123",
                bairro = "Centro",
                cidade = "São Paulo",
                cep = "12345678",
                pontoReferencia = "Próximo ao parque",
            )
        ),
        ResponsavelDTO(
            id = 2,
            nome = "Adriana",
            telefone = "123456789",
            parentesco = "Pai",
            cpf = "12345678900",
            endereco = EnderecoDTO(
                id = 1,
                logradouro = "Rua A",
                numero = "123",
                bairro = "Centro",
                cidade = "São Paulo",
                cep = "12345678",
                pontoReferencia = "Próximo ao parque",
            )
        )
    )

    ModalSelecionarResponsavel(
        responsaveis = responsaveis,
        onConfirmarClick = {},
        onResponsavelClick = {},
        isSelected = {
            responsaveis.contains(it)
        }
    )
    
}