package org.sopt.and.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import org.sopt.and.R
import org.sopt.and.core.designsystem.component.textfield.UserInfoTextField
import org.sopt.and.core.extension.showsnackBar
import org.sopt.and.core.extension.toast
import org.sopt.and.presentation.signup.SignUpViewModel.Companion.EXTRA_SIGNUP_IMAGE_LIST

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun LogInRoute(
    navigateToSignUp: () -> Unit,
    navigateToHome: () -> Unit,
    viewModel: LogInViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is LogInContract.LogInSideEffect.ShowToast -> {
                        context.toast(sideEffect.message)
                    }

                    is LogInContract.LogInSideEffect.ShowSnackBar -> {
                        snackBarHostState.showsnackBar(
                            sideEffect.message,
                            context
                        )
                    }

                    is LogInContract.LogInSideEffect.NavigateToSignUp -> {
                        navigateToSignUp()
                    }

                    is LogInContract.LogInSideEffect.NavigateToHome -> {
                        navigateToHome()
                    }
                }
            }
    }
    LogInScreen(
        logInUiState = uiState,
        snackBarHostState = snackBarHostState,
        onEmailValueChanged = { emailValue ->
            viewModel.setEvent(
                LogInContract.LogInEvent.OnEmailValueChanged(
                    emailValue = emailValue
                )
            )
        },
        onPasswordValueChanged = { passwordValue ->
            viewModel.setEvent(
                LogInContract.LogInEvent.OnPasswordValueChanged(
                    passwordValue = passwordValue
                )
            )
        },
        onLogInButtonClicked = {
            viewModel.setEvent(
                LogInContract.LogInEvent.OnLogInButtonClicked
            )
        },
        onSignUpButtonClicked = {
            viewModel.setEvent(
                LogInContract.LogInEvent.OnSignUpButtonClicked
            )
        }
    )
}

@ExperimentalPermissionsApi
@Composable
fun LogInScreen(
    logInUiState: LogInContract.LoginUiState,
    snackBarHostState: SnackbarHostState,
    onEmailValueChanged: (String) -> Unit = {},
    onPasswordValueChanged: (String) -> Unit = {},
    onLogInButtonClicked: () -> Unit = {},
    onSignUpButtonClicked: () -> Unit = {},
    onPasswordVisibleButtonClicked: (Boolean) -> Unit = {}
) {
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = Icons.AutoMirrored.Filled.KeyboardArrowLeft.name,
                    tint = Color.White
                )
                Image(
                    painter = painterResource(R.drawable.img_main_logo),
                    contentDescription = Icons.Default.AccountCircle.name,
                    modifier = Modifier
                        .width(110.dp)
                        .height(30.dp)
                )
                Spacer(Modifier)
            }
            Spacer(Modifier.padding(40.dp))

            UserInfoTextField(
                textField = logInUiState.emailValue,
                onValueChange = onEmailValueChanged,
                placeholder = stringResource(R.string.logintextfield_placeholder),
                isShown = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                )
            )

            Spacer(Modifier.padding(5.dp))

            UserInfoTextField(
                textField = logInUiState.passwordValue,
                onValueChange = onPasswordValueChanged,
                placeholder = stringResource(R.string.password),
                isShown = logInUiState.isPasswordVisible,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                ),
                trailingIcon = {
                    TextButton(
                        onClick = {
                            onPasswordVisibleButtonClicked(logInUiState.isPasswordVisible)
                        }) {
                        if (logInUiState.isPasswordVisible) {
                            Text(
                                text = stringResource(R.string.hide),
                                modifier = Modifier.padding(7.dp),
                                color = Color.White
                            )
                        } else {
                            Text(
                                text = stringResource(R.string.show),
                                modifier = Modifier.padding(7.dp),
                                color = Color.White

                            )
                        }
                    }
                }
            )


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 30.dp)
            ) {
                Button(
                    onClick = {
                        onLogInButtonClicked()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 50.dp),
                    colors = ButtonColors(
                        containerColor = colorResource(R.color.login_button_blue),
                        contentColor = Color.White,
                        disabledContentColor = Color.Blue,
                        disabledContainerColor = Color.White
                    )
                ) {
                    Text(text = stringResource(R.string.login))
                }
                TextButton(
                    onClick = {
                        onSignUpButtonClicked()
                    }
                ) {
                    Text(
                        text = stringResource(R.string.do_sign_up),
                        color = colorResource(R.color.gray_a3)
                    )
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

