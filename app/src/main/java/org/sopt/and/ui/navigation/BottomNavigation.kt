package org.sopt.and.ui.navigation

import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import org.sopt.and.R


@Composable
fun MyBottomNavigation(
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val items = listOf(
        BottomNavItem.NavHome,
        BottomNavItem.NavSearch,
        BottomNavItem.NavMyProfile
    )
    NavigationBar(
        modifier = Modifier.heightIn(max = 60.dp),
        containerColor = Color.Black
    ) {
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
                colors = NavigationBarItemColors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    selectedIndicatorColor = Color.White,
                    unselectedIconColor = colorResource(R.color.bar_item_white),
                    unselectedTextColor = colorResource(R.color.bar_item_white),
                    disabledIconColor =
                    colorResource(R.color.bar_item_white),
                    disabledTextColor =
                    colorResource(R.color.bar_item_white)
                ),
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