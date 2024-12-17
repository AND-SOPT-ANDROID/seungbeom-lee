package org.sopt.and.presentation.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import org.sopt.and.R
import org.sopt.and.core.designsystem.component.textfield.UserInfoTextField
import org.sopt.and.core.extension.serverToast
import org.sopt.and.core.extension.toast
import org.sopt.and.presentation.util.image.SignUpImage

@Composable
fun SignUpRoute(
    navigateToLogIn: () -> Unit,
    navigateToBack: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is SignUpContract.SignUpSideEffect.ShowToast -> {
                        context.toast(sideEffect.message)
                    }

                    is SignUpContract.SignUpSideEffect.ShowServerToastMessage -> {
                        context.serverToast(sideEffect.message)
                    }

                    is SignUpContract.SignUpSideEffect.NavigateToLogIn -> {
                        navigateToLogIn()
                    }

                    is SignUpContract.SignUpSideEffect.NavigateToBack -> {
                        navigateToBack()
                    }
                }
            }
    }

    SignUpScreen(
        signUpUiState = uiState,
        snackBarHostState = snackBarHostState,
        onEmailValueChanged = { emailValue ->
            viewModel.setEvent(SignUpContract.SignUpEvent.OnEmailValueChanged(emailValue = emailValue))
        },
        onPasswordValueChanged = { passwordValue ->
            viewModel.setEvent(SignUpContract.SignUpEvent.OnPasswordValueChanged(passwordValue = passwordValue))
        },
        onHobbyValueChanged = { hobbyValue ->
            viewModel.setEvent(SignUpContract.SignUpEvent.OnHobbyValueChanged(hobbyValue = hobbyValue))
        },
        onPasswordVisibleButtonClicked = {
            viewModel.setEvent(SignUpContract.SignUpEvent.OnPasswordVisibleButtonClicked)
        },
        onBackButtonClicked = {
            viewModel.setEvent(SignUpContract.SignUpEvent.OnBackButtonClicked)
        },
        onSignupButtonClicked = {
            viewModel.setEvent(SignUpContract.SignUpEvent.OnSignUpButtonClicked)
        }
    )
}

@Composable
fun SignUpScreen(
    signUpUiState: SignUpContract.SignUpUiState,
    snackBarHostState: SnackbarHostState,
    onEmailValueChanged: (String) -> Unit = {},
    onPasswordValueChanged: (String) -> Unit = {},
    onHobbyValueChanged: (String) -> Unit = {},
    onPasswordVisibleButtonClicked: () -> Unit = {},
    onSignupButtonClicked: () -> Unit = {},
    onBackButtonClicked: () -> Unit = {}

) {


    Scaffold(snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        content = { innerPadding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = colorResource(R.color.basic_background))
                    .padding(innerPadding)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
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
                            fontSize = 20.sp,
                            modifier = Modifier,
                            color = colorResource(R.color.white)
                        )
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = Icons.Default.Clear.name,
                            modifier = Modifier
                                .size(30.dp)
                                .clickable {
                                    onBackButtonClicked()
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
                            text = stringResource(R.string.enjoy_wavve_id_signup),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 20.dp),
                            color = colorResource(R.color.white)

                        )
                    }

                    Spacer(Modifier.padding(13.dp))

                    UserInfoTextField(
                        textField = signUpUiState.emailValue,
                        onValueChange = onEmailValueChanged,
                        placeholder = stringResource(R.string.wavve_email),
                        isShown = true,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                        ),
                        infoText = stringResource(R.string.please_enter_correct_signup),
                    )

                    UserInfoTextField(
                        textField = signUpUiState.passwordValue,
                        onValueChange = onPasswordValueChanged,
                        placeholder = stringResource(R.string.set_wavve_password_signup),
                        isShown = signUpUiState.isPasswordVisible,
                        trailingIcon = {
                            TextButton(
                                onClick = onPasswordVisibleButtonClicked,
                                content = {
                                    if (signUpUiState.isPasswordVisible) {
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
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                        ),
                        infoText = stringResource(R.string.signup_password_check_regex),

                        ) {
                    }

                    UserInfoTextField(
                        textField = signUpUiState.hobbyValue,
                        onValueChange = onHobbyValueChanged,
                        placeholder = stringResource(R.string.sign_up_hobby_basketball),
                        isShown = true,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                        ),
                        infoText = stringResource(R.string.sign_up_please_enter_under_8_hobby),
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Spacer(
                            Modifier
                                .height(1.dp)
                                .width(100.dp)
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
                                .width(100.dp)
                                .background(color = colorResource(R.color.gray_a3))
                        )

                    }

                    Row {
                        SignUpImage.EXTRA_SIGNUP_IMAGE_LIST.forEach { item ->
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

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 60.dp, max = 80.dp)
                        .background(color = colorResource(R.color.signup_button_gray))
                        .alpha(0.7f)
                        .clickable {
                            onSignupButtonClicked()
                        }
                ) {
                    Text(
                        text = stringResource(R.string.wavve_signup),
                        color = Color.White,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    )
}




