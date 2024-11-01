package org.sopt.and.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import org.sopt.and.ui.home.HomeScreen
import org.sopt.and.ui.login.LogInScreen
import org.sopt.and.ui.myprofile.MyProfileScreen
import org.sopt.and.ui.search.SearchScreen
import org.sopt.and.ui.signup.SignUpScreen
import org.sopt.and.ui.signup.UserInfo


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NavGraph(
    navController: NavHostController, bottomBarVisible: (Boolean) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Route.LogIn("", "")
    ) {
        composable<Route.LogIn> { backStackEntry ->
            val item = backStackEntry.toRoute<Route.LogIn>()
            LogInScreen(
                navigateToMyPage = { email, password ->
                    navController.navigate(Route.MyProFile(email, password)) {
                        popUpTo<Route.LogIn> {
                            inclusive = true
                        }
                    }
                    bottomBarVisible(true)
                },
                navigateToSignUp = {
                    navController.navigate(Route.SignUp)
                },
                signUpState = UserInfo(item.email, item.password)
            )
        }

        composable<Route.SignUp> {
            SignUpScreen(
                navigateToLogIn = { id, password ->
                    navController.navigate(Route.LogIn(id, password)) {
                        popUpTo(Route.SignUp) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                navigateToBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<Route.MyProFile> { backStackEntry ->
            val item = backStackEntry.toRoute<Route.MyProFile>()
            MyProfileScreen(item.email, item.password)
        }
        composable<Route.Home> {
            HomeScreen()
        }
        composable<Route.Search> {
            SearchScreen()
        }
    }
}


