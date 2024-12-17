package org.sopt.and.presentation.signup

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.data.dataremote.model.request.RequestUserInfoRegisterDto
import org.sopt.and.domain.usecase.RegisterUserUseCase
import org.sopt.and.presentation.util.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val registerUser: RegisterUserUseCase
) : BaseViewModel<SignUpContract.SignUpUiState, SignUpContract.SignUpSideEffect, SignUpContract.SignUpEvent>() {
    override fun createInitialState(): SignUpContract.SignUpUiState = SignUpContract.SignUpUiState()

    override suspend fun handleEvent(event: SignUpContract.SignUpEvent) {
        when (event) {
            is SignUpContract.SignUpEvent.OnSignUpButtonClicked -> {
                registerUserInfo()
            }

            is SignUpContract.SignUpEvent.OnEmailValueChanged -> {
                setState { copy(emailValue = event.emailValue) }
            }

            is SignUpContract.SignUpEvent.OnPasswordValueChanged -> {
                setState { copy(passwordValue = event.passwordValue) }
            }

            is SignUpContract.SignUpEvent.OnPasswordVisibleButtonClicked -> {
                setState { copy(isPasswordVisible = !isPasswordVisible) }
            }

            is SignUpContract.SignUpEvent.OnHobbyValueChanged -> {
                setState { copy(hobbyValue = event.hobbyValue) }
            }

            is SignUpContract.SignUpEvent.OnBackButtonClicked -> {
                setSideEffect(SignUpContract.SignUpSideEffect.NavigateToBack)
            }
        }
    }


    private fun registerUserInfo() {
        viewModelScope.launch {
            registerUser(
                RequestUserInfoRegisterDto(
                    uiState.value.emailValue,
                    uiState.value.passwordValue,
                    uiState.value.hobbyValue,
                )
            ).onFailure {
                setSideEffect(SignUpContract.SignUpSideEffect.ShowServerToastMessage(it.message))
            }.onSuccess {
                setSideEffect(SignUpContract.SignUpSideEffect.ShowToast(R.string.sign_up_signup_success))
                setSideEffect(SignUpContract.SignUpSideEffect.NavigateToLogIn)
            }
        }
    }
}

