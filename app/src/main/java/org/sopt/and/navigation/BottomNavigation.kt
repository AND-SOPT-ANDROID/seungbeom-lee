package org.sopt.and.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import org.sopt.and.R
import org.sopt.and.screen.Home
import org.sopt.and.screen.MyProFile
import org.sopt.and.screen.Search


sealed class BottomNavItem(
    @StringRes val title: Int,
    val icon: ImageVector,
    val screenRoute: Any
) {
    data object NavHome : BottomNavItem(R.string.home, Icons.Default.Home, Home)
    data object NavSearch: BottomNavItem(R.string.search, Icons.Default.Search, Search)
    data object NavMyProfile :
        BottomNavItem(R.string.myprofile, Icons.Default.AccountCircle, MyProFile(""))
}

@Composable
fun MyBottomNavigation(
    navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val items = listOf(
        BottomNavItem.NavHome,
        BottomNavItem.NavSearch,
        BottomNavItem.NavMyProfile
    )
    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.screenRoute,
                label = {
                    Text(
                        text = stringResource(id = item.title),
                        style = androidx.compose.ui.text.TextStyle(
                            fontSize = 12.sp
                        )
                    )
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.icon.name
                    )
                },
                onClick = {
                    navController.navigate(item.screenRoute) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}