package org.sopt.and.feature.main.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import org.sopt.and.feature.home.navigation.homeNavGraph
import org.sopt.and.feature.login.navigation.signInNavGraph
import org.sopt.and.feature.myprofile.navigation.myNavGraph
import org.sopt.and.feature.search.navigation.searchNavGraph
import org.sopt.and.feature.signup.navigation.signUpNavGraph


@Composable
fun NavGraph(
    padding: PaddingValues,
    navigator: MainNavigator
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination,
            exitTransition = {
                ExitTransition.None
            },
            popEnterTransition = {
                EnterTransition.None
            },
            enterTransition = {
                EnterTransition.None
            },
            popExitTransition = {
                ExitTransition.None
            }
        ) {
            signInNavGraph(
                navigationToSignUp = { navigator.navigationToSignUp() },
                navigationToMyPage = { logInState -> navigator.navigationToMyPage(logInState) }
            )

            signUpNavGraph(
                navigationToLogIn = { userInfo -> navigator.navigationToSignIn(userInfo) },
                navigationToBack = { navigator.naviagationToBack() }
            )

            homeNavGraph()
            myNavGraph()
            searchNavGraph()
        }
    }
}


