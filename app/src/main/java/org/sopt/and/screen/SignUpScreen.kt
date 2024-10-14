package org.sopt.and.screen

import android.app.Activity
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.component.SignUpTextField

val passwordRegex =
    "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#\$%^&+=!])(?=\\S+$).{8,20}$".toRegex()

@Composable
fun SignUpScreen(onRegister: (String, String) -> Unit, modifier: Modifier = Modifier) {
    var showPassword by remember { mutableStateOf(false) }
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    fun dataCheck() {
        id = id.trim()
        password = password.trim()
        if (!Patterns.EMAIL_ADDRESS.matcher(id).matches()) {
            Toast.makeText(context, "이메일 형식에 맞지 않습니다.", Toast.LENGTH_SHORT).show()
        } else if (!passwordRegex.matches(password)) {
            Toast.makeText(
                context, " 비밀번호는 8~20자 이내 영문 대소문 , 숫자, 특수문자 중\" +\n" +
                        "            \"\n 3가지 이상 혼용하여 사용해주세요.", Toast.LENGTH_SHORT
            ).show()
        } else {
            onRegister(id, password)
            Toast.makeText(context, "회원가입에 성공하였습니다", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.basic_background))
            .padding(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(Modifier)
            Text(
                text = stringResource(R.string.signup),
                fontSize = 20.sp, modifier = Modifier, color = colorResource(R.color.white)
            )
            Icon(
                imageVector = Icons.Default.Clear, contentDescription = Icons.Default.Clear.name,
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        (context as Activity).finish()
                    },
                tint = colorResource(R.color.exit)
            )
        }

        Spacer(Modifier.padding(13.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "이메일과 비밀번호만으로" +
                        "\n" + "Wavve를 즐길 수 있어요!",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 20.dp),
                color = colorResource(R.color.white)

            )
        }

        Spacer(Modifier.padding(13.dp))

        SignUpTextField(
            "로그인, 비밀번호 찾기,알림에 사용되니 정확한 이메일을 입력해\n주세요",
            id,
            { text: String -> id = text },
            "wavve@example.com",
            true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
            )
        )
        SignUpTextField(
            "비밀번호는 8~20자 이내 영문 대소문 , 숫자, 특수문자 중" +
                    "\n 3가지 이상 혼용하여 사용해주세요",
            password,
            { text: String -> password = text },
            "Wavve 비밀번호 설정",

            isShown = showPassword,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
            )
        ) {
            TextButton(
                onClick = { showPassword = !showPassword },
                content = {
                    if (showPassword) {
                        Text(
                            text = stringResource(R.string.hide), modifier = modifier.padding(7.dp)
                        )
                    } else {
                        Text(
                            text = stringResource(R.string.show), modifier = modifier.padding(7.dp)
                        )
                    }
                }
            )
        }

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
                modifier = modifier
                    .padding(3.dp)
                    .size(50.dp)
            )

            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = Icons.Default.CheckCircle.name,
                modifier = modifier
                    .padding(3.dp)
                    .size(50.dp)
            )

            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = Icons.Default.CheckCircle.name,
                modifier = modifier
                    .padding(3.dp)
                    .size(50.dp)
            )

            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = Icons.Default.CheckCircle.name,
                modifier = modifier
                    .padding(3.dp)
                    .size(50.dp)
            )

            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = Icons.Default.CheckCircle.name,
                modifier = modifier
                    .padding(3.dp)
                    .size(50.dp)
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = Icons.Default.Info.name,
                modifier.size(5.dp)
            )
            Spacer(modifier.padding(2.dp))
            Text(
                text = "SNS계정으로 간편하게 가입하여 이용하실 수 있습니다. 기",
                fontSize = 14.sp,
                color = colorResource(R.color.gray_63),
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Text(
            text = "  존 POOQ 계정 또는 Wavve 게정과는 연동되지 않으 이용에" +
                    "\n  참고 부탁드립니다.",
            fontSize = 14.sp,
            color = colorResource(R.color.gray_63),
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier.padding(100.dp))
        OutlinedButton(
            onClick = { dataCheck() },
            modifier = modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.gray71)
            )
        ) {
            Text(text = "Wavve 화원가입")
        }
    }
}


