package com.example.mobilevan.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.mobilevan.ui.theme.AzulVann

@Composable
fun ModalInformacoesAdicionais(
    nome: String = "João da Silva",
    telefone: String = "(11) 99999-9999",
    escola: String = "Escola Municipal de Educação",
    responsavel: String = "Maria da Silva",
    parentesco: String = "Tia",
    rua: String = "Rua das Flores",
    numero: String = "123",
    visible: Boolean = false,
    onDismissRequest: () -> Unit,
    onConfirmClick: () -> Unit
) {

    if (!visible) return

    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = nome,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                // Add content for additional information here

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Telefone: $telefone",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Text(
                    text = "Escola: $escola",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Text(
                    text = "Responsável: $responsavel, $parentesco",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Text(
                    text = "Endereço: $rua, $numero",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { onConfirmClick() },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(containerColor = AzulVann)
                ) {
                    Text(text = "Retornar")
                }
            }
        }
    }
}

@Preview
@Composable
private fun prev() {
    ModalInformacoesAdicionais(
        onDismissRequest = {},
        onConfirmClick = {}
    )
}