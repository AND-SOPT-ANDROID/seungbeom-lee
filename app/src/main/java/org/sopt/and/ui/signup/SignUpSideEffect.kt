package org.sopt.and.ui.signup

import androidx.annotation.StringRes

sealed class SignUpSideEffect {
    data class ShowToast(@StringRes val message: Int) : SignUpSideEffect()
    data class ShowSnackBar(@StringRes val message: Int) : SignUpSideEffect()
}