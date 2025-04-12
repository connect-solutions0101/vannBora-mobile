package com.example.mobilevan.ui.screens.feature_criar_trajeto

import HomeTopBar
import SearchBarPrev
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobilevan.ui.components.CardAluno
import com.example.mobilevan.ui.theme.AmareloVann
import com.example.mobilevan.ui.theme.AzulVann

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                CriarTrajetoPrev()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CriarTrajetoPrev() {
    CriarTrajeto()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CriarTrajeto(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HomeTopBar(
            title = "Olá Roberto",
            onNavigationIconClick = {},
            onActionIconClick = {},
            containerColor = AzulVann
        )
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 56.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SearchBarPrev()
            Text(
                text = "Disponíveis",
                color = AzulVann,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            // Column com scroll
            Column(
                modifier = Modifier
                    .heightIn(min = 0.dp, max = 400.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CardAluno()
                CardAluno()
                CardAluno()
                CardAluno()
                CardAluno()
                CardAluno()
                CardAluno()
                CardAluno()
                CardAluno()
                CardAluno()
                CardAluno()
                CardAluno()
                CardAluno()
                CardAluno()
                CardAluno()
                CardAluno()
                CardAluno()
                CardAluno()
                CardAluno()
                CardAluno()
                CardAluno()
                CardAluno()
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 64.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AmareloVann,
                        contentColor = Color.Black,
                    ),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    onClick = {},
                ) {
                    Text(text = "Novo Trajeto", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }
            }
        }
    }
}

