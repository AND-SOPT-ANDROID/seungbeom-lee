package org.sopt.and.screen

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import org.sopt.and.MyActivity
import org.sopt.and.R
import org.sopt.and.SignUpActivity
import org.sopt.and.component.TextField_Custom2

@Composable
fun LogInScreen(
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current

    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var signUpId by remember { mutableStateOf("") }
    var signUpPassword by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            signUpId = result.data?.getStringExtra("id") ?: ""
            signUpPassword = result.data?.getStringExtra("password") ?: ""
        }
    }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(R.color.basic_background))
                .padding(innerPadding)
                .padding(10.dp)
        ) {
            Text(
                text = "Wavve",
                color = colorResource(R.color.white),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.padding(40.dp))

            TextField_Custom2(
                id,
                { text: String -> id = text },
                "이메일 주소 또는 아이디",
                true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                )
            )

            Spacer(Modifier.padding(5.dp))

            TextField_Custom2(
                password,
                { text: String -> password = text },
                "비밀번호",
                showed = showPassword,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                )
            ) {
                TextButton(
                    onClick = {
                        showPassword = !showPassword
                    },
                    content = {
                        Text(
                            text = "show", modifier = modifier.padding(7.dp)
                        )
                    }
                )
            }


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 30.dp)
            ) {
                Button(
                    onClick = {
                        if (signUpId.isNotEmpty()) {
                            if (id != signUpId || password != signUpPassword) {
                                scope.launch {
                                    val result = snackbarHostState
                                        .showSnackbar(
                                            message = "아이디와 비밀번호를 확인해주세요",
                                            actionLabel = "확인하기",
                                            duration = SnackbarDuration.Short
                                        )
                                    when (result) {
                                        SnackbarResult.ActionPerformed -> {
                                            Toast.makeText(
                                                context,
                                                "id :$signUpId\npassword :$signUpPassword",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                        SnackbarResult.Dismissed -> {
                                        }
                                    }
                                }
                            } else {
                                scope.launch {
                                    Toast.makeText(context, "로그인 하였습니다", Toast.LENGTH_SHORT).show()
                                }
                                val intent2 = Intent(context, MyActivity::class.java).apply {
                                    this.putExtra("profileid", id)
                                    flags =
                                        Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                }
                                context.startActivity(intent2)
                            }
                        } else {
                            scope.launch {
                                val result = snackbarHostState
                                    .showSnackbar(
                                        message = "회원가입을 진행해주세요",
                                        actionLabel = "회원가입하기",
                                        duration = SnackbarDuration.Short
                                    )
                                when (result) {
                                    SnackbarResult.ActionPerformed -> {
                                        val intent = Intent(context, SignUpActivity::class.java)
                                        launcher.launch(intent)
                                    }

                                    SnackbarResult.Dismissed -> {
                                    }
                                }
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text("로그인")
                }
                TextButton(
                    onClick = {
                        val intent = Intent(context, SignUpActivity::class.java)
                        launcher.launch(intent)
                    }
                ) {
                    Text("회원가입하기")
                }
            }

            Spacer(Modifier.padding(10.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(
                    Modifier
                        .height(1.dp)
                        .width(70.dp)
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
                        .width(70.dp)
                        .background(color = colorResource(R.color.gray_a3))
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(15.dp)
            ) {
                Image(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = Icons.Default.CheckCircle.name,
                    modifier = modifier.size(50.dp)
                )

                Spacer(Modifier.padding(3.dp))

                Image(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = Icons.Default.CheckCircle.name,
                    modifier = modifier.size(50.dp)
                )

                Spacer(Modifier.padding(3.dp))

                Image(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = Icons.Default.CheckCircle.name,
                    modifier = modifier.size(50.dp)
                )
                Spacer(modifier.padding(3.dp))

                Image(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = Icons.Default.CheckCircle.name,
                    modifier = modifier.size(50.dp)
                )
                Spacer(Modifier.padding(3.dp))

                Image(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = Icons.Default.CheckCircle.name,
                    modifier = modifier.size(50.dp)
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    imageVector = Icons.Default.Info,
                    contentDescription = Icons.Default.Info.name,
                    modifier = Modifier.size(5.dp)
                )
                Spacer(modifier.padding(2.dp))
                Text(
                    text = "  SNS계정으로 간편하게 가입하여 이용하실 수 있습니다. 기",
                    fontSize = 14.sp,
                    color = colorResource(R.color.gray_63),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Text(
                text = "    존 POOQ 계정 또는 Wavve 게정과는 연동되지 않으 이용에" +
                        "\n    참고 부탁드립니다.",
                fontSize = 14.sp,
                color = colorResource(R.color.gray_63),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}




@Preview
@Composable
private fun SignInScreenPreview() {
    LogInScreen()
}