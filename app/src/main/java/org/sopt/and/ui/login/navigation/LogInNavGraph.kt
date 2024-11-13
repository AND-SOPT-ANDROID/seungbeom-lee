package org.sopt.and.ui.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import org.sopt.and.ui.login.LogInScreen
import org.sopt.and.ui.login.LogInState
import org.sopt.and.ui.navigation.Route
import org.sopt.and.ui.signup.SignUpState

@OptIn(ExperimentalPermissionsApi::class)
fun NavGraphBuilder.signInNavGraph(
    navigationToSignUp: () -> Unit = {},
    navigationToMyPage: (logInState:LogInState) -> Unit = {_-> }
) {
    composable<Route.LogIn> { backStackEntry ->
        val item = backStackEntry.toRoute<Route.LogIn>()
        val signUpState = SignUpState(item.email, item.password)
        LogInScreen(
            navigateToSignUp = navigationToSignUp,
            navigateToMyPage = navigationToMyPage,
            signUpState = signUpState
        )
    }
}