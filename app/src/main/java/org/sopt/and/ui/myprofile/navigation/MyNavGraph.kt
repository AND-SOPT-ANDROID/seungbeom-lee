package org.sopt.and.ui.myprofile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import org.sopt.and.ui.login.LogInState
import org.sopt.and.ui.myprofile.MyProfileScreen
import org.sopt.and.ui.navigation.MainTabRoute
import org.sopt.and.ui.navigation.Route

fun NavHostController.navigateToMy(navOptions: NavOptions) =
    navigate(MainTabRoute.MyProFile("",""), navOptions)

fun NavGraphBuilder.myNavGraph() {
    composable<MainTabRoute.MyProFile> { backStackEntry ->
        val item = backStackEntry.toRoute<Route.LogIn>()
        val logInState = LogInState(item.email, item.password)
        MyProfileScreen(logInState)
    }
}
