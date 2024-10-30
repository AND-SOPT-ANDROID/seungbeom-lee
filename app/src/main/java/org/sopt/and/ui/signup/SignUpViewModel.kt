package org.sopt.and.ui.signup

import android.util.Patterns
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignUpViewModel : ViewModel() {
    private val _signupState = MutableStateFlow(SignUpState())
    val signupState = _signupState.asStateFlow()

    private val _snackbarMessage = mutableStateOf("")
    val snackbarMessage: State<String> = _snackbarMessage

    fun setEmail(email: String) {
        _signupState.update {
            it.copy(
                email = email
            )
        }
    }

    fun setPassword(password: String) {
        _signupState.update {
            it.copy(
                password = password
            )
        }
    }

    fun isIdValid(): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(_signupState.value.email).matches()

    fun isPasswordValid(): Boolean {
        val password = _signupState.value.password

        if (password.length in PASSWORD_LENGTH_MIN..PASSWORD_LENGTH_MAX) {
            var count = 0
            if (password.contains(UPPER_CASE_REGEX.toRegex())) count++
            if (password.contains(LOWER_CASE_REGEX.toRegex())) count++
            if (password.contains(NUMBER_REGEX.toRegex())) count++
            if (password.contains(SPECIAL_CHAR_REGEX.toRegex())) count++

            if (count >= PASSWORD_TYPE) return true
        }
        return false
    }

    fun dataCheck(navigateToLogIn : (id:String,password:String)-> Unit){
        when {
            !isIdValid() ->
                _snackbarMessage.value = "잘못된 이메일 형식입니다."

            !isPasswordValid() ->
                _snackbarMessage.value = "잘못된 비밀번호 형식입니다."

            else -> {
                _snackbarMessage.value = "회원가입 되었습니다."
                navigateToLogIn(_signupState.value.email,_signupState.value.password)
            }
        }
    }

    fun clearSnackbarMessage() {
        _snackbarMessage.value = ""
    }

    companion object {
        private const val PASSWORD_LENGTH_MIN = 8
        private const val PASSWORD_LENGTH_MAX = 20
        private const val PASSWORD_TYPE = 3

        const val UPPER_CASE_REGEX = "[A-Z]"
        const val LOWER_CASE_REGEX = "[a-z]"
        const val NUMBER_REGEX = "[0-9]"
        const val SPECIAL_CHAR_REGEX = "[!@#\$%^&*(),.?\":{}|<>]"

        val EXTRA_SIGNUP_IMAGE_LIST = listOf(
            Icons.Default.CheckCircle,
            Icons.Default.CheckCircle,
            Icons.Default.CheckCircle,
            Icons.Default.CheckCircle,
            Icons.Default.CheckCircle
        )
    }
}

data class SignUpState(
    val email: String = "",
    val password: String = ""
)