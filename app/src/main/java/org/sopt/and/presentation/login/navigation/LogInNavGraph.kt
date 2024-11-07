package org.sopt.and.presentation.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import org.sopt.and.core.navigation.Route
import org.sopt.and.domain.entity.UserToken
import org.sopt.and.presentation.login.LogInScreen

@OptIn(ExperimentalPermissionsApi::class)
fun NavGraphBuilder.signInNavGraph(
    navigationToSignUp: () -> Unit = {},
    navigationToMyPage: (userToken:UserToken) -> Unit = { _-> }
) {
    composable<Route.LogIn> {
        LogInScreen(
            navigateToSignUp = navigationToSignUp,
            navigateToMyPage = navigationToMyPage,
        )
    }
}