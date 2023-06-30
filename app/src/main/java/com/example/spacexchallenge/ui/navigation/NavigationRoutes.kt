package com.example.spacexchallenge.ui.navigation

enum class NavigationRoutes(val route: String) {
    HomeRoute("HOME_ROUTE"),
    DetailsRoute("DETAILS_ROUTE");

    companion object {
        const val LAUNCH_ID = "launchId"
    }
}