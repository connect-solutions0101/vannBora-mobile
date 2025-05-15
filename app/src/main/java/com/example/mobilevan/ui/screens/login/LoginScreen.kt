package com.example.mobilevan.ui.screens.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mobilevan.R
import com.example.mobilevan.ui.navigation.Routes
import com.example.mobilevan.ui.theme.AzulVann
import com.example.mobilevan.ui.theme.CinzaVann
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: MainViewModel = viewModel()
) {
    val shape = RoundedCornerShape(16.dp)
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFF5F5F5))
            .imePadding(),  // Ajusta a tela quando o teclado aparece
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
            value = viewModel.email,
            onValueChange = {
                viewModel.email = it
            },
            label = { Text("E-mail") },
            modifier = Modifier
                .fillMaxWidth()  // Preenche toda a largura disponível
                .wrapContentHeight(),  // Ajusta a altura automaticamente
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = CinzaVann,
                focusedBorderColor = AzulVann,
                unfocusedBorderColor = AzulVann,
                focusedLabelColor = AzulVann,
                unfocusedLabelColor = AzulVann,
                focusedTextColor = AzulVann,
                unfocusedTextColor = AzulVann,
                cursorColor = AzulVann,
            ),
            shape = shape
        )

        Spacer(modifier = Modifier.height(66.dp))

        OutlinedTextField(
            value = viewModel.password,
            onValueChange = {
                viewModel.password = it
            },
            label = { Text("Senha") },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = CinzaVann,
                focusedBorderColor = AzulVann,
                unfocusedBorderColor = AzulVann,
                focusedLabelColor = AzulVann,
                unfocusedLabelColor = AzulVann,
                focusedTextColor = AzulVann,
                unfocusedTextColor = AzulVann,
                cursorColor = AzulVann,
            ),
            shape = shape,
            visualTransformation = PasswordVisualTransformation()  // Esconde os caracteres com asteriscos
        )

        Spacer(modifier = Modifier.height(66.dp))

        Button(
            onClick = {
                scope.launch {
                    val success = viewModel.onLoginClick(context)
                    if (success) {
                        navController.navigate(Routes.SelecionarTrajeto.route)
                    } else {
                        Toast.makeText(context, "Credenciais incorretas", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()  // Preenche toda a largura disponível
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFBBF24)),
            shape = shape
        ) {
            Text(text = "Acessar", color = Color.Black, fontWeight = FontWeight.SemiBold)
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            onClick = {navController.navigate(Routes.NovoTrajeto.route)},
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
@Preview()
@Composable
fun PreviewTelaLogin() {
    LoginScreen(navController = rememberNavController())
}
