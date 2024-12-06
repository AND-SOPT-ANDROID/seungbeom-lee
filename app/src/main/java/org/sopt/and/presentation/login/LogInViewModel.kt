package org.sopt.and.presentation.login

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.data.dataremote.model.request.RequestSignInDto
import org.sopt.and.domain.usecase.SetTokenUseCase
import org.sopt.and.domain.usecase.SignInUserUseCase
import org.sopt.and.presentation.util.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val loginValidation: SignInUserUseCase,
    private val setToken: SetTokenUseCase
) : BaseViewModel<LogInContract.LoginUiState, LogInContract.LogInSideEffect, LogInContract.LogInEvent>() {
    override fun createInitialState(): LogInContract.LoginUiState = LogInContract.LoginUiState()

    override suspend fun handleEvent(event: LogInContract.LogInEvent) {
        when (event) {
            is LogInContract.LogInEvent.OnLogInButtonClicked -> {
                loginWithValidation()
            }

            is LogInContract.LogInEvent.OnSignUpButtonClicked -> {
                setSideEffect(LogInContract.LogInSideEffect.NavigateToSignUp)
            }

            is LogInContract.LogInEvent.OnEmailValueChanged -> {
                setState { copy(emailValue = event.emailValue) }
            }

            is LogInContract.LogInEvent.OnPasswordValueChanged -> {
                setState { copy(passwordValue = event.passwordValue) }
            }

            is LogInContract.LogInEvent.OnPasswordVisibleButtonClicked -> {
                setState { copy(isPasswordVisible = !isPasswordVisible) }
            }
        }
    }

    private fun loginWithValidation() {
        viewModelScope.launch {
            loginValidation(
                RequestSignInDto(
                    currentState.emailValue,
                    currentState.passwordValue
                )
            ).onFailure {
                setSideEffect(LogInContract.LogInSideEffect.ShowSnackBar(R.string.check_id_password))
            }.onSuccess { response ->
                setSideEffect(LogInContract.LogInSideEffect.ShowToast(R.string.login_success_toast))
                setToken(response.token)
                setSideEffect(LogInContract.LogInSideEffect.NavigateToHome)
            }
        }
    }
}
