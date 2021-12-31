package au.com.onactionexample.navigation

sealed class Screen(val route: String) {
    object HomeUi : Screen("home_ui_screen")
    object DetailUi : Screen("detail_ui_screen")

    fun args(vararg args: String) = buildString {
        append(route)
        args.forEach { args ->
            append("/$args")
        }
    }
}