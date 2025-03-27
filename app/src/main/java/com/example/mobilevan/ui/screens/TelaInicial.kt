package com.example.mobilevan.ui.screens

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
import com.example.mobilevan.R

@Composable
fun TrajetosVaziosScreen(nomeUsuario: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFEFEFEF))
    ) {
        TopBar(nomeUsuario)

        Spacer(modifier = Modifier.height(40.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Parece que vc\n" +
                        "ainda não tem\n" +
                        "nenhum trajeto",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.height(80.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(style = androidx.compose.ui.text.SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)) {
                        append("Vannbora")
                    }
                    withStyle(style = androidx.compose.ui.text.SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(" criar um\n")
                    }
                    withStyle(style = androidx.compose.ui.text.SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)) {
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

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 53.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { /* TODO: Implementar ação */ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFC107)),
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
            .width(412.dp)
            .height(90.dp)
            .background(
                color = Color(0xFF001F3F),
                shape = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp) // 🔥 Agora só arredonda embaixo!
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
            painter = painterResource(R.drawable.person), // ⚠ Substitua pelo nome real do ícone
            contentDescription = "Ícone do usuário",
            modifier = Modifier.size(40.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTrajetosVaziosScreen() {
    TrajetosVaziosScreen("Roberto")
}
