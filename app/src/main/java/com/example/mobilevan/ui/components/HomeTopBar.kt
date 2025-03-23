import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobilevan.ui.screens.NovoTrajetoPrev
import com.example.mobilevan.ui.theme.AzulVann

class Main : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                NovoTrajetoPrev()
            }
        }
    }
}

@Preview
@Composable
fun HomeTopBarPrev(){
    HomeTopBar(title = "Olá Roberto", onNavigationIconClick = {}, onActionIconClick = {}, containerColor = AzulVann,)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    title: String,
    onNavigationIconClick: () -> Unit,
    onActionIconClick: () -> Unit,
    navigationIcon: @Composable () -> Unit = {
        IconButton(onClick = onNavigationIconClick) {
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = "Home"
            )
        }
    },
    actionIcon: @Composable () -> Unit = {
        IconButton(onClick = onActionIconClick) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "Perfil do Usuário"
            )
        }
    },
    containerColor: Color,
    titleContentColor: Color = Color.White,
    navigationIconContentColor: Color = Color.White,
    actionIconContentColor: Color = Color.White
) {
    CenterAlignedTopAppBar(
        modifier = Modifier.clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = containerColor,
            titleContentColor = titleContentColor,
            navigationIconContentColor = navigationIconContentColor,
            actionIconContentColor = actionIconContentColor
        ),
        title = {
            Text(text = title)
        },
        navigationIcon = navigationIcon,
        actions = {
            actionIcon()
        }
    )
}