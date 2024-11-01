package org.sopt.and.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import org.sopt.and.R


sealed class BottomNavItem(
    @StringRes val title: Int,
    val icon: ImageVector,
    val screenRoute: Any
) {
    data object NavHome : BottomNavItem(R.string.home, Icons.Default.Home, Route.Home)
    data object NavSearch : BottomNavItem(R.string.search, Icons.Default.Search, Route.Search)
    data object NavMyProfile :
        BottomNavItem(R.string.myprofile, Icons.Default.AccountCircle, Route.MyProFile("", ""))
}
