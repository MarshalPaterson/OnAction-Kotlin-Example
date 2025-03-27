package au.com.onactionexample.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import au.com.onactionexample.components.detail.DetailUi
import au.com.onactionexample.components.home.HomeUi

@Composable
fun Navigation() {
    val navController = rememberNavController()

    // nav host
    NavHost(navController = navController, startDestination = Screen.HomeUi.route) {
        // create screens
        composable(route = Screen.HomeUi.route) {
            HomeUi(navController = navController)
        }

        composable(
            route = Screen.DetailUi.route + "/{url}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                }
            )
        ) { entry ->
            DetailUi(url = entry.arguments!!.getString("url")!!)
        }
    }
}