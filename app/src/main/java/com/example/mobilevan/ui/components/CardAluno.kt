package com.example.mobilevan.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
fun CardAluno(
    nome: String,
    escola: String,
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
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = nome,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = escola,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Checkbox(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(24.dp),
                checked = isSelected,
                colors = CheckboxDefaults.colors(
                    checkedColor = AzulVann,
                    uncheckedColor = Color(0xFFFBBF24),
                    checkmarkColor = Color(0xFFFBBF24)
                ),
                onCheckedChange = null,
            )
        }

    }
}

@Preview
@Composable
fun CardAlunoPrev() {
    CardAluno(
        isSelected = true,
        nome = "Victor",
        escola = "SPTECH",
        onClick = {}
    )
}