
package udeg.cuvalles.r7_viewmodel

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import udeg.cuvalles.r7_viewmodel.screens.HomeScreen
import udeg.cuvalles.r7_viewmodel.screens.GameScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                onStartGame = { playerName ->
                    navController.navigate(
                        "game/${Uri.encode(playerName)}"
                    )
                }
            )
        }

        composable(
            route = "game/{playerName}",
            arguments = listOf(
                navArgument("playerName") {
                    type = NavType.StringType
                }
            )
        ) { entry ->

            val playerName =
                entry.arguments?.getString("playerName") ?: ""

            GameScreen(
                playerName = playerName,
                onExit = {
                    navController.popBackStack()
                }
            )
        }
    }
}
