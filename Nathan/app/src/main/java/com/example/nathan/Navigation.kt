package com.example.nathan.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.nathan.ui.screen.home.AppDetailScreen
import com.example.nathan.ui.screen.home.HomeScreen

// Define route constants
object Routes {
    const val HOME = "home"
    const val APP_DETAIL = "app_detail/{appName}"
}

@Composable
fun Navigation(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Routes.HOME) {
        // Home Screen Route
        composable(Routes.HOME) {
            HomeScreen(
                onAppClick = { appName ->
                    // Navigate to App Detail Screen when an app is clicked
                    navController.navigate("app_detail/$appName")
                }
            )
        }

        // App Detail Screen Route
        composable(
            route = Routes.APP_DETAIL,
            arguments = listOf(navArgument("appName") { type = NavType.StringType })
        ) { backStackEntry ->
            val appName = backStackEntry.arguments?.getString("appName") ?: ""
            AppDetailScreen(
                navController = navController,
                appName = appName
            )
        }
    }
}