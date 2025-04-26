import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.*
import com.example.mobilevan.ui.navigation.Routes
import com.example.mobilevan.ui.screens.NovoTrajeto
import com.example.mobilevan.ui.screens.feature_login.TelaLogin
import com.example.mobilevan.ui.screens.feature_selecionar_trajeto.TrajetoScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "login") {
                composable(Routes.Login.routes) { TelaLogin(navController) }
                composable(Routes.TelaInicial.routes) { TrajetoScreen(navController) }
                composable(Routes.NovoTrajeto.routes) { NovoTrajeto(navController) }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable(Routes.Login.routes) { TelaLogin(navController) }
        composable(Routes.TelaInicial.routes) { TrajetoScreen(navController) }
        composable(Routes.NovoTrajeto.routes) { NovoTrajeto(navController) }
    }
}
