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
import org.sopt.and.ui.navigation.BottomNavigation
import org.sopt.and.ui.navigation.NavGraph

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val mainViewModel = viewModel<MainViewModel>()
    val isVisible = mainViewModel.bottomBarVisible.collectAsStateWithLifecycle()
    val bottomBarVisible = isVisible.value
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            if (bottomBarVisible) {
                BottomNavigation(navController = navController)
            }
        }
    ) { innerPadding ->
        Box(
            modifier = when (bottomBarVisible) {
                true -> Modifier
                    .padding(innerPadding)
                    .fillMaxSize()

                false -> Modifier.fillMaxSize()
            }
        ) {
            NavGraph(navController) { visible ->
                mainViewModel.setBarVisible(visible)
            }
        }
    }
}