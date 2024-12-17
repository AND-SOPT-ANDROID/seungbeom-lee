package org.sopt.and.presentation.myprofile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.and.core.navigation.MainTabRoute
import org.sopt.and.presentation.myprofile.MyProfileRoute

fun NavHostController.navigateToMy(navOptions: NavOptions) =
    navigate(MainTabRoute.MyProFile, navOptions)

fun NavGraphBuilder.myNavGraph() {
    composable<MainTabRoute.MyProFile> {
        MyProfileRoute()
    }
}
