package au.com.onactionexample.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import au.com.onactionexample.components.detail.DetailUI
import au.com.onactionexample.components.home.HomeUI

@Composable
fun Navigation() {
    val navController = rememberNavController()

    // nav host
    NavHost(navController = navController, startDestination = Screen.HomeUI.route) {
        // create screens
        composable(route = Screen.HomeUI.route) {
            HomeUI(navController = navController)
        }

        composable(
            // /{name} must
            // ?name={name} optional
            route = Screen.DetailUI.route + "/{name}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = "Raheem"
                    nullable = false
                }
            )
        ) { entry ->
            DetailUI(text = entry.arguments!!.getString("name")!!)
        }
    }
}