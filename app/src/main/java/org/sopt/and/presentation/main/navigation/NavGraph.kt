package org.sopt.and.presentation.main.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import org.sopt.and.presentation.home.navigation.homeNavGraph
import org.sopt.and.presentation.login.navigation.signInNavGraph
import org.sopt.and.presentation.myprofile.navigation.myNavGraph
import org.sopt.and.presentation.search.navigation.searchNavGraph
import org.sopt.and.presentation.signup.navigation.signUpNavGraph


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
                navigationToMyPage = { navigator.navigationToMyPage() }
            )

            signUpNavGraph(
                navigationToLogIn = { navigator.navigationToSignIn() },
                navigationToBack = { navigator.naviagationToBack() }
            )

            homeNavGraph()
            myNavGraph()
            searchNavGraph()
        }
    }
}


