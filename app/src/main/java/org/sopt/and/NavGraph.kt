package org.sopt.and

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import org.sopt.and.screen.LogIn
import org.sopt.and.screen.LogInScreen
import org.sopt.and.screen.MyPage
import org.sopt.and.screen.MyProfileScreen
import org.sopt.and.screen.SignUp
import org.sopt.and.screen.SignUpScreen


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = LogIn) {
        composable<LogIn> { backStackEntry ->
            val signupState = backStackEntry.toRoute<LogIn>()
            LogInScreen(
                navigateToMyPage = {
                    navController.navigate(MyPage)
                },
                navigateToSignUp = {
                    navController.navigate(SignUp)
                },
                signUpState = signupState
            )
        }

        composable<SignUp> {
            SignUpScreen(
                navigateToLogIn = { id, password ->
                    navController.navigate(LogIn(id, password))
                }
            )
        }

        composable<MyPage> {
            MyProfileScreen()
        }
    }
}


