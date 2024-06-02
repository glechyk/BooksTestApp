package com.books.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.books.app.presentation.feature.details.DetailsRoute
import com.books.app.presentation.feature.main.MainRoute
import com.books.app.presentation.feature.splash.SplashRoute
import com.books.app.utils.DETAILS_ARGUMENT
import com.books.app.utils.SCREEN_DETAILS
import com.books.app.utils.SCREEN_MAIN
import com.books.app.utils.SCREEN_SPLASH

sealed class Screen(val route: String) {
    data object Splash : Screen(SCREEN_SPLASH)
    data object Main : Screen(SCREEN_MAIN)
    data object Details : Screen(SCREEN_DETAILS)
}

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Splash.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Splash.route) {
            SplashRoute(
                onNavigateToMainScreen = { navController.navigate(Screen.Main.route) }
            )
        }
        composable(Screen.Main.route) {
            MainRoute(
                onNavigateToDetails = {
                    navController.navigate(Screen.Details.route + it)
                }
            )
        }
        composable(
            Screen.Details.route + "{$DETAILS_ARGUMENT}",
            arguments = listOf(navArgument(DETAILS_ARGUMENT) { type = NavType.IntType }),
        ) {
            val bookId = it.arguments?.getInt(DETAILS_ARGUMENT) ?: -1
            DetailsRoute(
                bookId = bookId,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}