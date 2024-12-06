package org.sopt.and.presentation.login

import androidx.annotation.StringRes
import org.sopt.and.presentation.util.base.UiEvent
import org.sopt.and.presentation.util.base.UiSideEffect
import org.sopt.and.presentation.util.base.UiState

class LogInContract {
    data class LoginUiState(
        val isPasswordVisible: Boolean = false,
        val emailValue: String = "",
        val passwordValue: String = ""
    ) : UiState

    sealed interface LogInSideEffect : UiSideEffect {
        data object NavigateToHome : LogInSideEffect
        data object NavigateToSignUp : LogInSideEffect
        data class ShowToast(@StringRes val message: Int) : LogInSideEffect
        data class ShowSnackBar(@StringRes val message: Int) : LogInSideEffect
    }

    sealed class LogInEvent : UiEvent {
        data class OnEmailValueChanged(val emailValue: String) : LogInEvent()
        data class OnPasswordValueChanged(val passwordValue: String) : LogInEvent()
        data class OnPasswordVisibleButtonClicked(val isPasswordVisible: Boolean) : LogInEvent()
        data object OnLogInButtonClicked : LogInEvent()
        data object OnSignUpButtonClicked : LogInEvent()
    }
}