package org.sopt.and.feature.main

import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.sopt.and.feature.main.navigation.rememberMainNavigator
import org.sopt.and.feature.main.component.MainBottomTabsBar
import org.sopt.and.feature.main.navigation.NavGraph

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navigator = rememberMainNavigator()

    Scaffold(
        bottomBar = {
            if (navigator.shouldShowBottomBar()) {
                MainBottomTabsBar(
                    mainTabs = MainTab.entries,
                    currentBottomTab = navigator.currentTab,
                    onTabClicked = { tab -> navigator.navigateMainTab(tab) }
                )
            }
        },
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets,
        containerColor = Color.Black,
        content = { innerPadding ->
            NavGraph(
                padding = innerPadding,
                navigator = navigator
            )
        }
    )
}