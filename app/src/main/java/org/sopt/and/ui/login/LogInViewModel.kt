package org.sopt.and.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.and.R

class LogInViewModel : ViewModel() {
    private val _loginState = MutableStateFlow(LogInState())
    val loginState = _loginState.asStateFlow()

    private val _signInSideEffect = MutableSharedFlow<SignInSideEffect>()
    val signInSideEffect get() = _signInSideEffect.asSharedFlow()

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

    private fun isSignInAvailable(email: String, password: String): Boolean =
        _loginState.value.email == email && _loginState.value.password == password


    fun checkLoginData(
        email: String,
        password: String,
        navigateToMyPage: (logInState: LogInState) -> Unit
    ) {
        viewModelScope.launch {
            when {
                email.isBlank() ->
                    _signInSideEffect.emit(SignInSideEffect.ShowSnackBar(R.string.please_signup))

                !isSignInAvailable(email, password) ->
                    _signInSideEffect.emit(SignInSideEffect.ShowSnackBar(R.string.check_id_password))

                else -> {
                    _signInSideEffect.emit(SignInSideEffect.ShowToast(R.string.login_success_toast))
                    navigateToMyPage(LogInState(email, password))
                }
            }
        }
    }
}
