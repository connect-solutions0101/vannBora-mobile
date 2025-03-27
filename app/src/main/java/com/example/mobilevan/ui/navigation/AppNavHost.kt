import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.*
import com.example.mobilevan.ui.screens.NovoTrajeto
import com.example.mobilevan.ui.screens.TelaLogin
import com.example.mobilevan.ui.screens.TrajetosVaziosScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "login") {
                composable("login") { TelaLogin(navController) }
                composable("tela_inicial") { TrajetosVaziosScreen(navController) }
                composable("novo_trajeto") { NovoTrajeto(navController) }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { TelaLogin(navController) }
        composable("tela_inicial") { TrajetosVaziosScreen(navController) }
        composable("novo_trajeto") { NovoTrajeto(navController) }
    }
}
