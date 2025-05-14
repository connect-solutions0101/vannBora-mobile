package com.example.mobilevan.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilevan.ui.theme.AzulVann

@Composable
fun CardResponsavel(
    nome: String,
    telefone: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color(0xFFFBBF24) else AzulVann,
            contentColor = if (isSelected) AzulVann else Color.White
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .size(width = 200.dp, height = 80.dp)
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = nome,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = telefone,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview
@Composable
fun CardResPrev() {
    CardResponsavel(
        nome = "Victor",
        telefone = "(11) 99999-9999",
        onClick = {}
    )
}