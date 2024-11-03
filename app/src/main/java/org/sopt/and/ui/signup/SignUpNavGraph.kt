package org.sopt.and.ui.signup

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.sopt.and.ui.navigation.Route

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