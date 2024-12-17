package org.sopt.and.presentation.signup

import androidx.annotation.StringRes
import org.sopt.and.presentation.util.base.UiEvent
import org.sopt.and.presentation.util.base.UiSideEffect
import org.sopt.and.presentation.util.base.UiState

class SignUpContract {
    data class SignUpUiState(
        val isPasswordVisible: Boolean = false,
        val emailValue: String = "",
        val passwordValue: String = "",
        val hobbyValue: String = ""
    ) : UiState

    sealed interface SignUpSideEffect : UiSideEffect {
        data object NavigateToLogIn : SignUpSideEffect
        data object NavigateToBack : SignUpSideEffect
        data class ShowToast(@StringRes val message: Int) : SignUpSideEffect
        data class ShowServerToastMessage(val message: String? = "") : SignUpSideEffect
    }

    sealed class SignUpEvent : UiEvent {
        data class OnEmailValueChanged(val emailValue: String) : SignUpEvent()
        data class OnPasswordValueChanged(val passwordValue: String) : SignUpEvent()
        data class OnHobbyValueChanged(val hobbyValue: String) : SignUpEvent()
        data object OnPasswordVisibleButtonClicked : SignUpEvent()
        data object OnSignUpButtonClicked : SignUpEvent()
        data object OnBackButtonClicked : SignUpEvent()
    }
}