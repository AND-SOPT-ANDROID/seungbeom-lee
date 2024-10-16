package org.sopt.and

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.sopt.and.screen.SignUpScreen

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignUpScreen(onRegister = { id, password ->
                val resultIntent = Intent().apply {
                    putExtra(SIGN_UP_ID, id.trim())
                    putExtra(SIGN_UP_PASSWORD, password.trim())
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            })

        }
    }

    companion object {
        const val SIGN_UP_ID: String = "signupid"
        const val SIGN_UP_PASSWORD: String = "signuppassword"
        const val PROFILE_ID: String = "profileid"
    }
}





