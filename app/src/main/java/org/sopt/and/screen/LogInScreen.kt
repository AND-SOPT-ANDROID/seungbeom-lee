package org.sopt.and.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.sopt.and.R
import org.sopt.and.component.LogInTextField
import org.sopt.and.findActivity
import org.sopt.and.viewmodel.LogInViewModel
import org.sopt.and.viewmodel.SignUpState
import org.sopt.and.viewmodel.SignUpViewModel.Companion.EXTRA_SIGNUP_IMAGE_LIST

@Serializable
data class LogIn(
    val email: String,
    val password: String
)

@ExperimentalPermissionsApi
@Composable
fun LogInScreen(
    navigateToSignUp: () -> Unit,
    navigateToMyPage: (String) -> Unit,
    signUpState: SignUpState
) {
    val viewModel = viewModel<LogInViewModel>()

    val context = LocalContext.current
    val activity = context.findActivity()

    val loginState = viewModel.loginState.collectAsStateWithLifecycle()
    val id = loginState.value.email
    val password = loginState.value.password

    var isPasswordVisible by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    fun logInFalse() {
        scope.launch {
            val result = snackBarHostState
                .showSnackbar(
                    message = activity.getString(R.string.check_id_password),
                    actionLabel = activity.getString(R.string.check),
                    duration = SnackbarDuration.Short
                )
            when (result) {
                SnackbarResult.ActionPerformed -> {
                    Toast.makeText(
                        activity,
                        "id :${signUpState.email}\npassword :${signUpState.password}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                SnackbarResult.Dismissed -> Unit
            }
        }
    }

    fun logInSuccess() {
        Toast.makeText(activity, context.getString(R.string.login_success_toast), Toast.LENGTH_SHORT).show()
        navigateToMyPage(id)
    }

    fun signUpCheck() {
        scope.launch {
            val result = snackBarHostState
                .showSnackbar(
                    message = context.getString(R.string.please_signup),
                    actionLabel = context.getString(R.string.do_signup),
                    duration = SnackbarDuration.Short
                )
            when (result) {
                SnackbarResult.ActionPerformed -> {
                    navigateToSignUp()
                }

                SnackbarResult.Dismissed -> Unit
            }
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
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
                text = stringResource(R.string.wavve),
                color = colorResource(R.color.white),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.padding(40.dp))

            LogInTextField(
                textField = id,
                onValueChange = viewModel::setEmail,
                placeholder = stringResource(R.string.logintextfield_placeholder),
                isShown = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                )
            )

            Spacer(Modifier.padding(5.dp))

            LogInTextField(
                textField = password,
                onValueChange = viewModel::setPassword,
                placeholder = stringResource(R.string.password),
                isShown = isPasswordVisible,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                )
            ) {
                TextButton(
                    onClick = {
                        isPasswordVisible = !isPasswordVisible
                    }) {
                    if (isPasswordVisible) {
                        Text(
                            text = stringResource(R.string.hide),
                            modifier = Modifier.padding(7.dp)
                        )
                    } else {
                        Text(
                            text = stringResource(R.string.show),
                            modifier = Modifier.padding(7.dp)
                        )
                    }
                }
            }


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 30.dp)
            ) {
                Button(
                    onClick = {
                        if (signUpState.email.isNotBlank()) {
                            if (!viewModel.checkLoginData(signUpState.email,signUpState.password)) {
                                logInFalse()
                            } else {
                                logInSuccess()
                            }
                        } else {
                            signUpCheck()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 50.dp)
                ) {
                    Text(text = stringResource(R.string.login))
                }
                TextButton(
                    onClick = {
                        navigateToSignUp()
                    }
                ) {
                    Text(text = stringResource(R.string.do_sign_up))
                }
            }

            Spacer(Modifier.height(10.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(
                    Modifier
                        .height(1.dp)
                        .weight(1f)
                        .background(color = colorResource(R.color.gray_a3))
                )
                Text(
                    text = stringResource(R.string.signup_another_state),
                    modifier = Modifier.padding(5.dp),
                    color = colorResource(R.color.gray_a3)
                )
                Spacer(
                    Modifier
                        .height(1.dp)
                        .weight(1f)
                        .background(color = colorResource(R.color.gray_a3))
                )
            }
            Row {
                EXTRA_SIGNUP_IMAGE_LIST.forEach { item ->
                    Image(
                        imageVector = item,
                        contentDescription = item.name,
                        modifier = Modifier
                            .padding(3.dp)
                            .size(50.dp)
                    )
                }
            }

            Row {
                Image(
                    imageVector = Icons.Default.Info,
                    contentDescription = Icons.Default.Info.name,
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .size(10.dp)
                )
                Spacer(Modifier.padding(2.dp))
                Text(
                    text = stringResource(R.string.sns_pooq_wavve),
                    fontSize = 14.sp,
                    color = colorResource(R.color.gray_63),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Preview
@Composable
private fun LoginScreenPreview() {
    LogInScreen({ }, {}, signUpState = SignUpState("",""))
}

