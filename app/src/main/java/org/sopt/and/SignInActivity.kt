package org.sopt.and

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.and.ui.theme.ANDANDROIDTheme

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var id: String? = null
        var password: String? = null
        val getResultText = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                id = result.data?.getStringExtra("id")
                password = result.data?.getStringExtra("password")
            }
        }
        super.onCreate(savedInstanceState)
        setContent {
            ANDANDROIDTheme {
                Welcome(
                    Modifier, onClick = {
                        val intent = Intent(this, SignUpActivity::class.java)
                        getResultText.launch(intent)
                    }, id, password
                )
            }
        }
    }
}


