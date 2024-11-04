package org.sopt.and.feature.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import org.sopt.and.core.navigation.MainTabRoute
import org.sopt.and.core.navigation.Route

enum class MainTab(
    val iconResId: ImageVector,
    val contentDescription: String,
    val route: MainTabRoute
) {
    HOME(
        iconResId = Icons.Default.Home,
        contentDescription = "홈",
        route = MainTabRoute.Home
    ),
    SEARCH(
        iconResId = Icons.Default.Search,
        contentDescription = "검색",
        route = MainTabRoute.Search
    ),
    MY(
        iconResId = Icons.Default.AccountCircle,
        contentDescription = "My",
        route = MainTabRoute.MyProFile("","")
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