package org.sopt.and

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.sopt.and.screen.SignUpScreen
import org.sopt.and.ui.theme.ANDANDROIDTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignUpScreen(onRegister = { id, password ->
                val resultIntent = Intent().apply {
                    putExtra(SIGNUPID, id.trim())
                    putExtra(SIGNUPPASSWORD, password.trim())
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            })

        }
    }

    companion object {
        const val SIGNUPID: String = "signupid"
        const val SIGNUPPASSWORD: String = "signuppassword"
        const val PROFILEID: String = "profileid"
    }
}





