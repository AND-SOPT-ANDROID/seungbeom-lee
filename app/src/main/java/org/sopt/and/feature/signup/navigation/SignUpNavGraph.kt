package org.sopt.and.feature.signup.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.and.core.navigation.Route
import org.sopt.and.feature.signup.SignUpScreen
import org.sopt.and.feature.signup.SignUpState

fun NavGraphBuilder.signUpNavGraph(
    navigationToLogIn: (signUpState: SignUpState) -> Unit = {},
    navigationToBack: () -> Unit
) {
    composable<Route.SignUp> {
        SignUpScreen(
            navigateToLogIn = navigationToLogIn,
            navigateToBack = navigationToBack
        )
    }
}