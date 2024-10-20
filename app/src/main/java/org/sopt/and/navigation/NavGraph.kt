package org.sopt.and.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import org.sopt.and.screen.Home
import org.sopt.and.screen.HomeScreen
import org.sopt.and.screen.LogIn
import org.sopt.and.screen.LogInScreen
import org.sopt.and.screen.MyProFile
import org.sopt.and.screen.MyProfileScreen
import org.sopt.and.screen.Search
import org.sopt.and.screen.SearchScreen
import org.sopt.and.screen.SignUp
import org.sopt.and.screen.SignUpScreen
import org.sopt.and.viewmodel.SignUpState


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NavGraph(navController: NavHostController, bottomBarVisible: (Boolean) -> Unit) {
    NavHost(navController = navController, startDestination = LogIn("", "")) {
        composable<LogIn> { backStackEntry ->
            val item = backStackEntry.toRoute<LogIn>()

            LogInScreen(
                navigateToMyPage = { email ->
                    navController.navigate(MyProFile(email)) {
                        popUpTo<LogIn> {
                            inclusive = true
                        }
                    }
                    bottomBarVisible(true)
                },
                navigateToSignUp = {
                    navController.navigate(SignUp)
                },
                signUpState = SignUpState(item.email, item.password)
            )
        }

        composable<SignUp> {
            SignUpScreen(
                navigateToLogIn = { id, password ->
                    navController.navigate(LogIn(id, password)) {
                        popUpTo(SignUp) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                navigateToBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<MyProFile> { backStackEntry ->
            val item = backStackEntry.toRoute<MyProFile>()
            MyProfileScreen(item.email)
        }
        composable<Home> {
            HomeScreen()
        }
        composable<Search> {
            SearchScreen()
        }
    }
}


