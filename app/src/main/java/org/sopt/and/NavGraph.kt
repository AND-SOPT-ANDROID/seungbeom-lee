package org.sopt.and

import androidx.compose.runtime.Composable
import androidx.compose.ui.input.key.Key.Companion.D
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.serialization.Serializable
import org.sopt.and.screen.LogInScreen
import org.sopt.and.screen.MyProfileScreen
import org.sopt.and.screen.SignUpScreen


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.LogIn) {
        composable<Screen.LogIn> {
            LogInScreen(
                navigateToMyPage = {
                    navController.navigate(MyProfileScreen())
                },
                navigateToSignUp = {
                    navController.navigate(SignUpScreen())
                }
            )
        }

        composable<Screen.SignUp> { backStackEntry ->
            SignUpScreen(
                navigateToC = { id, password ->
                    navController.navigate(C(id, password))
                }
            )
        }

        composable<Screen.MyPage> {
            MyProfileScreen(
            )
        }
    }
}

@Serializable
sealed class Screen() {
    @Serializable
    data object LogIn

    @Serializable
    data object SignUp

    @Serializable
    data object MyPage
}
