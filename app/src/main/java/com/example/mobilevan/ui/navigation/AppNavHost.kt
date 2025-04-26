import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.*
import com.example.mobilevan.ui.navigation.Routes
import com.example.mobilevan.ui.screens.NovoTrajeto
import com.example.mobilevan.ui.screens.TelaLogin
import com.example.mobilevan.ui.screens.TrajetoScreen
import com.example.mobilevan.ui.screens.TrajetoAlunoScreen
import com.example.mobilevan.ui.screens.feature_clima.ClimaScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = Routes.Login.toString()) {
                composable(Routes.Login.toString()) { TelaLogin(navController) }
                composable(Routes.NovoTrajeto.toString()) { NovoTrajeto(navController) }
                composable(Routes.SelecionarTrajeto.toString()) { TrajetoScreen(navController) }
                composable(Routes.Trajetos.toString()) { TrajetoAlunoScreen(navController)}
                composable(Routes.ClimaScreen.toString()) { ClimaScreen(navController) }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable(Routes.Login.toString()) { TelaLogin(navController) }
        composable(Routes.NovoTrajeto.toString()) { NovoTrajeto(navController) }
        composable(Routes.SelecionarTrajeto.toString()) { TrajetoScreen(navController) }
        composable(Routes.Trajetos.toString()) { TrajetoAlunoScreen(navController)}
        composable(Routes.ClimaScreen.toString()) { ClimaScreen(navController)}
    }
}
