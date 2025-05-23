package com.example.mobilevan.ui.screens.clima

import HomeTopBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import com.example.mobilevan.store.TokenStore
import com.example.mobilevan.ui.navigation.Routes
import com.example.mobilevan.ui.theme.AzulVann
import kotlinx.coroutines.launch

@Composable
fun ClimaScreen(
    navController: NavHostController,
    viewModel: MainViewModel = viewModel()
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.fetchWeather(context)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {

        HomeTopBar(
            title = "Olá ${viewModel.nomeUsuario}",
            onNavigationIconClick = {
                navController.navigate(Routes.SelecionarTrajeto.route)
            },
            onActionIconClick = {
                coroutineScope.launch {
                    TokenStore.clear(context)
                    navController.navigate(Routes.Login.route)
                }
            },
            containerColor = AzulVann,
        )

        Spacer(modifier = Modifier.height(40.dp))

        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(24.dp)
        ) {
            Column {

                Text(
                    text = viewModel.weather?.results?.city ?: "Carregando...",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(20.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${viewModel.weather?.results?.temp ?: "--"}°",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(viewModel.weather?.results?.description ?: "--", fontSize = 14.sp)
                        Text(viewModel.weather?.results?.date ?: "--", fontSize = 14.sp)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            contentAlignment = Alignment.Center
        ) {
            SvgImageFromHGBrasil(
                condition = viewModel.weather?.results?.condition_slug ?: "none_day",
                description = viewModel.weather?.results?.description ?: "---",
                imageLoader = rememberSvgImageLoader(),
                modifier = Modifier.size(100.dp)
            )
        }

        Spacer(modifier = Modifier.height(75.dp))


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color(0xFFDCEAFF), RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Text("Próximos 6 dias", fontWeight = FontWeight.SemiBold)

            Spacer(modifier = Modifier.height(8.dp))

            viewModel.weather?.results?.forecast?.take(6)?.forEach { forecast ->
                DiaClimaItem(
                    dia = "${forecast.weekday}, ${forecast.date}",
                    temp = "${forecast.max}°C",
                    condition = forecast.condition,
                    description = forecast.description
                )
            }
        }

//        Spacer(modifier = Modifier.weight(1f))
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 36.dp),
//            horizontalArrangement = Arrangement.Center
//        ) {
//            androidx.compose.material.IconButton(
//                onClick = { },
//                modifier = Modifier.padding(end = 16.dp)
//            ) {
//                androidx.compose.material.Icon(
//                    painter = painterResource(id = R.drawable.cloud),
//                    contentDescription = "Nuvem",
//                    tint = Color.Black,
//                    modifier = Modifier.size(50.dp)
//                )
//            }
//            Spacer(modifier = Modifier.width(150.dp))
//            androidx.compose.material.IconButton(onClick = { }) {
//                androidx.compose.material.Icon(
//                    painter = painterResource(id = R.drawable.x),
//                    contentDescription = "Fechar",
//                    tint = Color.Black,
//                    modifier = Modifier.size(50.dp)
//                )
//            }
//        }
    }
}

@Composable
fun DiaClimaItem(dia: String, temp: String, condition: String, description: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .background(Color(0xFF0D1F4E), RoundedCornerShape(20.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(dia, color = Color.White)
        Row(verticalAlignment = Alignment.CenterVertically) {
            SvgImageFromHGBrasil(
                condition = condition,
                description = description,
                imageLoader = rememberSvgImageLoader(),
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(temp, color = Color.White)
        }
    }
}

@Composable
fun rememberSvgImageLoader(): ImageLoader {
    val context = LocalContext.current
    return remember {
        ImageLoader.Builder(context)
            .components {
                add(SvgDecoder.Factory())
            }
            .build()
    }
}

@Composable
fun SvgImageFromHGBrasil(
    condition: String,
    description: String,
    imageLoader: ImageLoader,
    modifier: Modifier
) {
    val context = LocalContext.current

    AsyncImage(
        model = "https://assets.hgbrasil.com/weather/icons/conditions/$condition.svg",
        contentDescription = description,
        imageLoader = imageLoader,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}

@Preview()
@Composable
fun ClimaScreenPrev() {
    ClimaScreen(navController = rememberNavController())
}
