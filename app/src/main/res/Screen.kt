sealed class Screen(val route: String) {
    object Home : Screen("login")
    object Profile : Screen("profile")
    object Settings : Screen("settings")
}
