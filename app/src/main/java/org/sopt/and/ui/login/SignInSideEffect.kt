package org.sopt.and.ui.login

import androidx.annotation.StringRes
import org.sopt.and.ui.signup.SignUpSideEffect

sealed class SignInSideEffect {
    data class ShowToast(@StringRes val message: Int) : SignInSideEffect()
    data class ShowSnackBar(@StringRes val message: Int) : SignInSideEffect()
}