package org.sopt.and

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.sopt.and.screen.LogInScreen
import org.sopt.and.ui.theme.ANDANDROIDTheme

class LogInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LogInScreen()
        }
    }
}


