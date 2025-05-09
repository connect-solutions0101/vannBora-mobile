package com.example.mobilevan.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilevan.ui.theme.AzulVann

@Composable
fun ModalSelecionarTrajeto(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    title: String,
    labelButton1: String,
    onSelecionarOpcao1: () -> Unit,
    labelButton2: String,
    onSelecionarOpcao2: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            containerColor = Color(0xFFF5F5F5),
            modifier = Modifier
                .background(
                    color = Color(0xFFF5F5F5),
                    shape = RoundedCornerShape(16.dp)
                ),
            onDismissRequest = { onDismiss() },
            title = { Text(
                text = title,
                color = AzulVann,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ) },
            dismissButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AzulVann,
                        contentColor = Color.White
                    ),
                    onClick = {
                        onSelecionarOpcao2()
                        onDismiss()
                }) {
                    Text(labelButton2)
                }
            },
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00400C),
                        contentColor = Color.White
                    ),
                    onClick = {
                        onSelecionarOpcao1()
                        onDismiss()
                    }) {
                    Text(labelButton1)
                }
            },
        )
    }
}

@Preview
@Composable
private fun modalPrev() {
    ModalSelecionarTrajeto(
        showDialog = true,
        onDismiss = { },
        title = "O que deseja fazer?",
        labelButton1 = "Iniciar",
        onSelecionarOpcao1 = { },
        labelButton2 = "Editar",
        onSelecionarOpcao2 = { }
    )
}