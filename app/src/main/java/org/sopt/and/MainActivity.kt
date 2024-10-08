package org.sopt.and

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.theme.ANDANDROIDTheme

class MainActivity : ComponentActivity() {

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

@Composable
fun Welcome(
    modifier: Modifier = Modifier, onClick: () -> Unit, signUpId: String?, signUpPassword: String?
) {

    val context = LocalContext.current
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    fun dataCheck(id: String) {
        if (id.length < 6) {
            Toast.makeText(context, "아이디는 6자리 이상 입력해주세요", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "로그인에 성공하였습니다", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Welcome to SOPT",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 20.dp)
        )
        Spacer(modifier = Modifier.padding(100.dp))
        PutSomething(Modifier, "ID", id, { text: String -> id = text }, "이메일 주소 또는 아이디")
        Spacer(Modifier.padding(20.dp))
        PutSomething(
            Modifier, "비밀번호", password, { text: String -> password = text }, "비밀번호",
            {
                Text(
                    text = "show",
                    modifier = modifier.padding(end = 10.dp)
                )
            },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier.padding(100.dp))
        if (!signUpId.isNullOrEmpty()) {
            Text(text = signUpId)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(30.dp)
        ) {
            TextButton(
                onClick = onClick
            ) {
                Text("회원가입하기")
            }
            Button(
                onClick = { dataCheck(id) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("로그인 하기")
            }
        }
    }
}

@Composable
fun PutSomething(
    modifier: Modifier = Modifier,
    text: String,
    textfield: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    Column(modifier = Modifier.padding(start = 30.dp, end = 30.dp)) {
        Text(
            text = text,
            modifier = modifier.fillMaxWidth()

        )
        TextField(
            value = textfield,
            onValueChange = onValueChange,
            trailingIcon = trailingIcon,
            placeholder = { Text(placeholder) },
            visualTransformation = visualTransformation,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview

@Composable

private fun WelcomPreview() {
    val id = ""
    val password = ""
    Welcome(onClick = { }, signUpId = id, signUpPassword = password)
}