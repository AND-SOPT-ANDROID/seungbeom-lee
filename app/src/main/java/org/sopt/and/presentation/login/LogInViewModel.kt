package org.sopt.and.presentation.login

import android.util.Log
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
import org.sopt.and.domain.usecase.SignInUserUseCase
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val signInUserUseCase: SignInUserUseCase
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


    private fun isSignInAvailable(email: String, password: String): Boolean =
        _loginState.value.userName == email && _loginState.value.password == password


    fun checkLoginData(
        navigateToMyPage: (userToken: UserToken) -> Unit
    ) {
        viewModelScope.launch {
            signInUser(
                RequestSignInDto(
                    _loginState.value.userName,
                    _loginState.value.password
                )
            ).onFailure { exception ->
                Log.d("ServerExeception", "등록 실패: ${exception.message}")
                _signInSideEffect.emit(SignInSideEffect.ShowSnackBar(R.string.check_id_password))
            }.onSuccess { response ->
                _signInSideEffect.emit(SignInSideEffect.ShowToast(R.string.login_success_toast))
                navigateToMyPage(response)

            }
        }
    }
}
