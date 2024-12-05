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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import org.sopt.and.core.extension.showsnackBar
import org.sopt.and.core.extension.toast
import org.sopt.and.presentation.signup.SignUpViewModel.Companion.EXTRA_SIGNUP_IMAGE_LIST


@Composable
fun SignUpScreen(
    navigateToLogIn: () -> Unit,
    navigateToBack: () -> Unit
) {
    val viewModel: SignUpViewModel = hiltViewModel()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var isPasswordVisible by remember { mutableStateOf(false) }

    val signUpState by viewModel.signupState.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel.signUpSideEffect, lifecycleOwner) {
        viewModel.signUpSideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is SignUpSideEffect.ShowToast -> {
                        context.toast(sideEffect.message)
                    }

                    is SignUpSideEffect.ShowSnackBar -> {
                        snackbarHostState.showsnackBar(
                            sideEffect.message,
                            context
                        )
                    }

                    is SignUpSideEffect.NavigateToLogIn -> {
                        navigateToLogIn()
                    }

                    is SignUpSideEffect.NaviagateToBack -> {
                        navigateToBack()
                    }
                }
            }
    }

    Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        content = { innerpadding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = colorResource(R.color.basic_background))
                    .padding(innerpadding)
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
                                    viewModel.navigateToBack()
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
                        textField = signUpState.username,
                        onValueChange = viewModel::setUserName,
                        placeholder = stringResource(R.string.wavve_email),
                        isShown = true,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                        ),
                        infoText = stringResource(R.string.please_enter_correct_signup),
                    )

                    UserInfoTextField(
                        textField = signUpState.password,
                        onValueChange = viewModel::setPassword,
                        placeholder = stringResource(R.string.set_wavve_password_signup),
                        isShown = isPasswordVisible,
                        trailingIcon = {
                            TextButton(
                                onClick = { isPasswordVisible = !isPasswordVisible },
                                content = {
                                    if (isPasswordVisible) {
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
                        textField = signUpState.hobby,
                        onValueChange = viewModel::setHobby,
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

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 60.dp, max = 80.dp)
                        .background(color = colorResource(R.color.signup_button_gray))
                        .alpha(0.7f)
                        .clickable {
                            viewModel.sendData()
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




