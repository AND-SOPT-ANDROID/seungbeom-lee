package org.sopt.and.ui.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.and.ui.home.HomeScreen
import org.sopt.and.ui.navigation.MainTabRoute

fun NavHostController.navigateToHome(navOptions: NavOptions) =
    navigate(MainTabRoute.Home, navOptions)

fun NavGraphBuilder.homeNavGraph() {
    composable<MainTabRoute.Home> {
        HomeScreen()
    }
}