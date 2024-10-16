package org.sopt.and.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.sopt.and.screen.passwordRegex

class LogInViewModel : ViewModel() {
    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()

    fun initLoginState() {
        _loginState.value = LoginState()
    }

    fun setEmail(email: String) {
        _loginState.update {
            it.copy(
                email = email
            )
        }
    }

    fun setPassword(password: String) {
        _loginState.update {
            it.copy(
                password = password
            )
        }
    }

    fun checkLoginData(loginState: LoginState): Boolean =
        _loginState.value == loginState

}

data class LoginState(
    val email: String = "",
    val password: String = ""
)