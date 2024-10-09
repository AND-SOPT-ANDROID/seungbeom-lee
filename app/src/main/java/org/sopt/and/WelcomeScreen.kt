package org.sopt.and

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun Welcome(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    loginClick: () -> Unit,
    signUpId: String?,
    signUpPassword: String?
) {

    val context = LocalContext.current
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    val cScope = rememberCoroutineScope()
    val snackBarHost = remember { SnackbarHostState() }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.basic_background))
            .padding(10.dp)
    ) {
        Text(
            text = "Wavve",
            color = colorResource(R.color.white),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold, modifier = Modifier

        )

        Spacer(modifier.padding(40.dp))

        TextField_Custom2(
            id,
            { text: String -> id = text },
            "이메일 주소 또는 아이디",
            true
        )

        Spacer(modifier.padding(5.dp))

        TextField_Custom2(
            password,
            { text: String -> password = text },
            "비밀번호",
            showed = showPassword,
        ) {
            TextButton(
                onClick = { showPassword = !showPassword },
                content = {
                    Text(
                        text = "show", modifier = modifier.padding(7.dp)
                    )
                }
            )
        }
        if (!signUpId.isNullOrEmpty()) {
            Text(text = signUpId)
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 30.dp)
        ) {
            Button(
                onClick = { loginClick() }
//                    if (!signUpId.isNullOrEmpty()) {
//                        if (id != signUpId || password != signUpPassword) {
//                            cScope.launch {
//                                val snackBar =
//                                    snackBarHost.showSnackbar(
//                                        "아이디 비밀번호가 일치하지 않습니다",
//                                        "닫기",
//                                        false,
//                                        SnackbarDuration.Short
//                                    )
//                                // result
//                                when (snackBar) {
//                                    SnackbarResult.ActionPerformed -> {}
//                                    SnackbarResult.Dismissed -> {}
//                                }
//                            }
//                        } else {
//                            Toast.makeText(context, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show()
//                            loginClcik()
//
//                        }
//                    }
                ,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("로그인")
            }
            TextButton(
                onClick = onClick
            ) {
                Text("회원가입하기")
            }
        }

        Spacer(modifier.padding(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(
                Modifier
                    .height(1.dp)
                    .width(100.dp)
                    .background(color = colorResource(R.color.gray_a3))
            )
            Text(
                text = "또는 다른 서비스 계정으로 가입",
                modifier = Modifier.padding(5.dp),
                color = colorResource(R.color.gray_a3)
            )
            Spacer(
                Modifier
                    .height(1.dp)
                    .width(100.dp)
                    .background(color = colorResource(R.color.gray_a3))
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(15.dp)
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = Icons.Default.CheckCircle.name,
                modifier = modifier.size(50.dp)
            )

            Spacer(modifier.padding(3.dp))

            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = Icons.Default.CheckCircle.name,
                modifier = modifier.size(50.dp)
            )

            Spacer(modifier.padding(3.dp))

            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = Icons.Default.CheckCircle.name,
                modifier = modifier.size(50.dp)
            )
            Spacer(modifier.padding(3.dp))

            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = Icons.Default.CheckCircle.name,
                modifier = modifier.size(50.dp)
            )
            Spacer(modifier.padding(3.dp))

            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = Icons.Default.CheckCircle.name,
                modifier = modifier.size(50.dp)
            )
            Spacer(modifier.padding(3.dp))
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = Icons.Default.Info.name,
                modifier.size(5.dp)
            )
            Spacer(modifier.padding(2.dp))
            Text(
                text = "  SNS계정으로 간편하게 가입하여 이용하실 수 있습니다. 기",
                fontSize = 14.sp,
                color = colorResource(R.color.gray_63),
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Text(
            text = "    존 POOQ 계정 또는 Wavve 게정과는 연동되지 않으 이용에" +
                    "\n    참고 부탁드립니다.",
            fontSize = 14.sp,
            color = colorResource(R.color.gray_63),
            modifier = Modifier
                .fillMaxWidth()
        )

    }
}


@Composable
fun TextField_Custom2(
    textfield: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    showed: Boolean,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    val showPassword = if (!showed) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None

    }
    Column(modifier = Modifier) {
        TextField(
            value = textfield,
            onValueChange = onValueChange,
            trailingIcon = trailingIcon,
            placeholder = { Text(placeholder) },
            visualTransformation = showPassword,
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(R.color.textfield_gray2f)
            )
        )
    }
}


@Preview
@Composable
private fun WelcomPreview() {
    val id = ""
    val password = ""
    Welcome(onClick = { }, loginClick = {}, signUpId = id, signUpPassword = password)
}