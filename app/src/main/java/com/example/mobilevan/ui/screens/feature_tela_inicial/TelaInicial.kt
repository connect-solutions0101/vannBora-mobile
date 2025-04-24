package com.example.mobilevan.ui.screens.feature_tela_inicial

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.withStyle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mobilevan.R
import com.example.mobilevan.ui.theme.AmareloVann
import com.example.mobilevan.ui.theme.AzulVann
import com.example.mobilevan.ui.theme.CinzaVann

@Composable
fun TrajetosVaziosScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CinzaVann)
            .padding(horizontal = 24.dp)
    ) {
        // TopBar
        TopBar(nomeUsuario = "Roberto")

        // Espaço para o título
        Spacer(modifier = Modifier.height(40.dp))

        // Texto que explica a ausência de trajetos
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Parece que vc\nainda não tem\nnenhum trajeto",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(80.dp))

            // Texto estilizado para criar um novo trajeto
            Text(
                text = buildAnnotatedString {
                    withStyle(style = androidx.compose.ui.text.SpanStyle(color = AzulVann, fontWeight = FontWeight.Bold)) {
                        append("Vannbora")
                    }
                    withStyle(style = androidx.compose.ui.text.SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(" criar um\n")
                    }
                    withStyle(style = androidx.compose.ui.text.SpanStyle(color = AzulVann, fontWeight = FontWeight.Bold)) {
                        append("Trajeto?")
                    }
                },
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Start
            )
        }

        // Botão para navegar para a tela de novo trajeto
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 53.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { navController.navigate("novo_trajeto") },
                colors = ButtonDefaults.buttonColors(backgroundColor = AmareloVann),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .height(50.dp)
                    .width(180.dp)
            ) {
                Text(
                    text = "+ Novo Trajeto",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun TopBar(nomeUsuario: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(
                color = AzulVann,
                shape = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)
            )
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Olá, $nomeUsuario",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )

        Image(
            painter = painterResource(R.drawable.person),
            contentDescription = "Ícone do usuário",
            modifier = Modifier.size(40.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTrajetosVaziosScreen() {
    TrajetosVaziosScreen(navController = rememberNavController())
}
