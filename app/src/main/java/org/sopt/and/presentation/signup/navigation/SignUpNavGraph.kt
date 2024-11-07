package org.sopt.and.presentation.signup.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.and.core.navigation.Route
import org.sopt.and.presentation.signup.SignUpScreen

fun NavGraphBuilder.signUpNavGraph(
    navigationToLogIn: () -> Unit = {},
    navigationToBack: () -> Unit = {}
) {
    composable<Route.SignUp> {
        SignUpScreen(
            navigateToLogIn = navigationToLogIn,
            navigateToBack = navigationToBack
        )
    }
}