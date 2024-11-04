package org.sopt.and.feature.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import org.sopt.and.feature.home.navigation.navigateToHome
import org.sopt.and.feature.login.LogInState
import org.sopt.and.feature.main.MainTab
import org.sopt.and.feature.myprofile.navigation.navigateToMy
import org.sopt.and.core.navigation.MainTabRoute
import org.sopt.and.core.navigation.Route
import org.sopt.and.feature.search.navigation.navigateToSearch
import org.sopt.and.feature.signup.SignUpState

class MainNavigator(
    val navController: NavHostController,
) {
    val startDestination = Route.LogIn(
        email = "",
        password = "",
    )

    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination


    val currentTab: MainTab?
        @Composable get() = MainTab.find { tab ->
            currentDestination?.hasRoute(tab::class) == true
        }

    private val navOptions = navOptions {
        launchSingleTop = true
    }

    fun navigationToSignUp() = navController.navigate(Route.SignUp, navOptions)

    fun navigationToMyPage(logInState: LogInState) = navController.navigate(MainTabRoute.MyProFile(logInState.email,logInState.password)) {
        popUpTo(Route.LogIn("", "")) {
            inclusive = true
        }
    }
    fun naviagationToBack() = navController.popBackStack()


    fun navigationToSignIn(signUpState: SignUpState) =
        navController.navigate(Route.LogIn(signUpState.email, signUpState.password), navOptions)

    fun navigateMainTab(tab: MainTab) {
        val navOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                inclusive = true
            }
            launchSingleTop = true
        }

        when (tab) {
            MainTab.SEARCH -> navController.navigateToSearch(navOptions)
            MainTab.HOME -> navController.navigateToHome(navOptions)
            MainTab.MY -> navController.navigateToMy(navOptions)
        }
    }

    @Composable
    fun shouldShowBottomBar() = MainTab.contains {
        currentDestination?.hasRoute(it::class) == true
    }
}

@Composable
fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}