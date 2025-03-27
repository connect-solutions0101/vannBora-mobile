package com.example.mobilevan.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mobilevan.R


@Composable
fun TelaLogin(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    val shape = RoundedCornerShape(16.dp)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Image(
            painter = painterResource(id = R.drawable.logobus),
            contentDescription = "Ícone de ônibus",
            modifier = Modifier
                .width(120.dp)
                .height(120.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Image(
            painter = painterResource(id = R.drawable.logotipo),
            contentDescription = "Logo VANN BORA",
            modifier = Modifier
                .width(250.dp)
                .height(60.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Crie e organize rotas",
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "dos seus alunos!",
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(96.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail") },
            modifier = Modifier
                .width(290.dp)
                .height(50.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            shape = shape
        )

        Spacer(modifier = Modifier.height(66.dp))

        OutlinedTextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Senha") },
            modifier = Modifier
                .width(290.dp)
                .height(50.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            shape = shape
        )

        Spacer(modifier = Modifier.height(66.dp))

        Button(
            onClick = {
                navController.navigate("tela_inicial")
            },
            modifier = Modifier
                .width(290.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD700)),
            shape = shape
        ) {
            Text(text = "Acessar", color = Color.Black, fontWeight = FontWeight.SemiBold)
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            onClick = { /* tela de cadastro */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(buildAnnotatedString {
                withStyle(SpanStyle(color = Color.Black)) {
                    append("Não tem uma conta ")
                }
                withStyle(SpanStyle(color = Color.Blue)) {
                    append("clique aqui!")
                }
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTelaLogin() {
    TelaLogin(navController = rememberNavController())
}

