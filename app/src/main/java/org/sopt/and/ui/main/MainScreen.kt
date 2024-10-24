package org.sopt.and.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import org.sopt.and.ui.navigation.MyBottomNavigation
import org.sopt.and.ui.navigation.NavGraph

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val mainViewModel = viewModel<MainViewModel>()
    val isVisible = mainViewModel.bottomBarVisible.collectAsStateWithLifecycle()
    val bottomBarVisible = isVisible.value.bottomBarVisible
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            if (bottomBarVisible) {
                MyBottomNavigation(navController = navController)
            }
        }
    ) { innerPadding ->
        Box(
            modifier = if (bottomBarVisible) {
                Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            } else {
                Modifier.fillMaxSize()
            }
        ) {
            NavGraph(navController) { visible ->
                mainViewModel.setBarVisible(visible)
            }
        }
    }
}