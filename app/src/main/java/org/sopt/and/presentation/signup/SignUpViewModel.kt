package org.sopt.and.presentation.signup

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
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
import org.sopt.and.data.dataremote.model.request.RequestUserInfoRegisterDto
import org.sopt.and.data.dataremote.model.response.ResponseUserInfoRegisterSuccessDto
import org.sopt.and.domain.entity.UserSignUpInfo
import org.sopt.and.domain.usecase.RegisterUserUseCase
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {
    private val _signupState = MutableStateFlow(UserSignUpInfo())
    val signupState = _signupState.asStateFlow()


    private val _signUpSideEffect = MutableSharedFlow<SignUpSideEffect>()
    val signUpSideEffect get() = _signUpSideEffect.asSharedFlow()

    fun setUserName(username: String) {
        _signupState.update {
            it.copy(
                username = username
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

    fun setHobby(hobby: String) {
        _signupState.update {
            it.copy(
                hobby = hobby
            )
        }
    }

    private suspend fun registerUser(request: RequestUserInfoRegisterDto):
            Result<ResponseUserInfoRegisterSuccessDto> = registerUserUseCase(request)


    private fun isUserNameValid(): Boolean =
        _signupState.value.username.length < USER_INFO_LENGTH_MAX

    private fun isHobbyValid(): Boolean =
        _signupState.value.hobby.length < USER_INFO_LENGTH_MAX

    private fun isPasswordValid(): Boolean {
        val password = _signupState.value.password

        if (password.length < USER_INFO_LENGTH_MAX) {
            var count = 0
            if (password.contains(UPPER_CASE_REGEX.toRegex())) count++
            if (password.contains(LOWER_CASE_REGEX.toRegex())) count++
            if (password.contains(NUMBER_REGEX.toRegex())) count++
            if (password.contains(SPECIAL_CHAR_REGEX.toRegex())) count++

            if (count >= PASSWORD_TYPE) return true
        }
        return false
    }


    fun dataCheck(navigateToLogIn: () -> Unit) {
        viewModelScope.launch {
            when {
                !isUserNameValid() ->
                    _signUpSideEffect.emit(SignUpSideEffect.ShowSnackBar(R.string.sign_up_not_valid_email))

                !isPasswordValid() ->
                    _signUpSideEffect.emit(SignUpSideEffect.ShowSnackBar(R.string.sign_up_not_valid_password))

                !isHobbyValid() ->
                    _signUpSideEffect.emit(SignUpSideEffect.ShowSnackBar(R.string.sign_up_not_valid_hobby))

                else -> {
                    registerUser(
                        RequestUserInfoRegisterDto(
                            _signupState.value.username,
                            _signupState.value.password,
                            _signupState.value.hobby
                        )
                    ).onFailure { exception ->
                        Log.d("ServerExeception", "등록 실패: ${exception.message}")
                    }.onSuccess {
                        _signUpSideEffect.emit(SignUpSideEffect.ShowToast(R.string.sign_up_signup_success))
                        navigateToLogIn()
                    }
                }
            }
        }
    }


    companion object {
        private const val USER_INFO_LENGTH_MAX = 8
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

