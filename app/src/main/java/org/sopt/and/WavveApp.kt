package org.sopt.and

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun WavveApp(navController:NavHostController = rememberNavController()) {
    NavGraph(navController = navController)
}