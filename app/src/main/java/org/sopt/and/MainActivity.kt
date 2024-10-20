package org.sopt.and

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import org.sopt.and.navigation.MyBottomNavigation
import org.sopt.and.navigation.NavGraph
import org.sopt.and.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mainViewModel = viewModel<MainViewModel>()
            val isVisible =mainViewModel.bottomBarVisible.collectAsStateWithLifecycle()
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
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) {
                    NavGraph(navController) { visible ->
                        mainViewModel.setBarVisible(visible)
                    }
                }
            }

        }
    }
}

internal fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("Permissions should be called in the context of an Activity")
}
