package com.example.spacexchallenge.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.spacexchallenge.ui.viewhome.HomePage
import com.example.spacexchallenge.ui.viewlaunchdetails.LaunchDetailsPage

@Composable
fun NavigationView() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.HomeRoute.route
    ) {
        composableForHome { launchId ->
            navController.navigate(route = NavigationRoutes.DetailsRoute.route + "/$launchId") {
                launchSingleTop = true
            }
        }
        composableForLaunchDetails {
            navController.popBackStack()
        }
    }
}

private fun NavGraphBuilder.composableForHome(
    navigate: (launchId: String) -> Unit
) {
    composable(
        route = NavigationRoutes.HomeRoute.route,
        content = {
            HomePage(
                onClick = { navigate(it) }
            )
        }
    )
}

private fun NavGraphBuilder.composableForLaunchDetails(
    onBackPress: () -> Unit
) {
    composable(
        route = NavigationRoutes.DetailsRoute.route + "/{${NavigationRoutes.LAUNCH_ID}}",
        arguments = listOf(navArgument(name = NavigationRoutes.LAUNCH_ID) {
            type = NavType.StringType
        }),
        content = { backStackEntry ->
            val launchId = backStackEntry.arguments?.getString(NavigationRoutes.LAUNCH_ID) ?: ""
            LaunchDetailsPage(
                launchId = launchId,
                onClickBack = onBackPress
            )
        }
    )
}