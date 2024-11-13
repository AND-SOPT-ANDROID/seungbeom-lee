package org.sopt.and.ui.search.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.sopt.and.ui.navigation.MainTabRoute
import org.sopt.and.ui.search.SearchScreen

fun NavHostController.navigateToSearch(navOptions: NavOptions) =
    navigate(MainTabRoute.Search, navOptions)

fun NavGraphBuilder.searchNavGraph() {
    composable<MainTabRoute.Search> {
        SearchScreen()
    }
}
