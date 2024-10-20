package org.sopt.and.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

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

    fun checkLoginData(email: String, password: String): Boolean =
        _loginState.value == LoginState(email, password)

}

data class LoginState(
    val email: String = "",
    val password: String = ""
)