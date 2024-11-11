package org.sopt.and.presentation.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import org.sopt.and.core.navigation.Route
import org.sopt.and.presentation.login.LogInScreen

fun NavHostController.navigateToLogIn(navOptions: NavOptions) {
    navigate(Route.LogIn, navOptions)
}

@OptIn(ExperimentalPermissionsApi::class)
fun NavGraphBuilder.signInNavGraph(
    navigationToSignUp: () -> Unit = {},
    navigationToMyPage: () -> Unit = {}
) {
    composable<Route.LogIn> {
        LogInScreen(
            navigateToSignUp = navigationToSignUp,
            navigateToMyPage = navigationToMyPage,
        )
    }
}