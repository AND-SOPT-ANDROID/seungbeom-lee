package org.sopt.and.presentation.main

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import org.sopt.and.R
import org.sopt.and.core.navigation.MainTabRoute
import org.sopt.and.core.navigation.Route

enum class MainTab(
    val iconImageVector: ImageVector,
    @StringRes val contentDescription: Int,
    val route: MainTabRoute
) {
    HOME(
        iconImageVector = Icons.Default.Home,
        contentDescription = R.string.main_tab_home,
        route = MainTabRoute.Home
    ),
    SEARCH(
        iconImageVector = Icons.Default.Search,
        contentDescription = R.string.main_tab_search,
        route = MainTabRoute.Search
    ),
    MY(
        iconImageVector = Icons.Default.AccountCircle,
        contentDescription = R.string.main_tab_my,
        route = MainTabRoute.MyProFile("")
    );

    companion object {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}