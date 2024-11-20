package org.sopt.and.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.data.dataremote.model.request.RequestSignInDto
import org.sopt.and.domain.entity.UserLogInInfo
import org.sopt.and.domain.entity.UserToken
import org.sopt.and.domain.usecase.SetTokenUseCase
import org.sopt.and.domain.usecase.SignInUserUseCase
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val signInUserUseCase: SignInUserUseCase,
    private val setTokenUseCase: SetTokenUseCase
) : ViewModel() {
    private val _loginState = MutableStateFlow(UserLogInInfo())
    val loginState = _loginState.asStateFlow()

    private val _signInSideEffect = MutableSharedFlow<SignInSideEffect>()
    val signInSideEffect get() = _signInSideEffect.asSharedFlow()


    fun setUserName(userName: String) {
        _loginState.update {
            it.copy(
                userName = userName
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

    private suspend fun signInUser(request: RequestSignInDto): Result<UserToken> =
        signInUserUseCase(request)

    private suspend fun setToken(token: String) {
        setTokenUseCase(token)
    }

    private fun navigateToHome() {
        viewModelScope.launch {
            _signInSideEffect.emit(SignInSideEffect.NavigateToHome)
        }
    }

    fun navigateToSignUp() {
        viewModelScope.launch {
            _signInSideEffect.emit(SignInSideEffect.NavigateToSignUp)
        }
    }

    fun checkLoginData() {
        viewModelScope.launch {
            signInUser(
                RequestSignInDto(
                    _loginState.value.userName,
                    _loginState.value.password
                )
            ).onFailure {
                _signInSideEffect.emit(SignInSideEffect.ShowSnackBar(R.string.check_id_password))
            }.onSuccess { response ->
                _signInSideEffect.emit(SignInSideEffect.ShowToast(R.string.login_success_toast))
                setToken(response.token)
                navigateToHome()
            }
        }
    }
}
