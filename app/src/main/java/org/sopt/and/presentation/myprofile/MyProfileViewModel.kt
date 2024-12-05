package org.sopt.and.presentation.myprofile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.and.domain.entity.UserHobby
import org.sopt.and.domain.entity.UserToken
import org.sopt.and.domain.usecase.GetTokenUseCase
import org.sopt.and.domain.usecase.GetUserHobbyUseCase
import javax.inject.Inject

@HiltViewModel
class MyProfileViewModel @Inject constructor(
    private val getUserHobbyUseCase: GetUserHobbyUseCase,
    private val getTokenUseCase: GetTokenUseCase
) : ViewModel() {
    private val _profileStatus = MutableStateFlow(ProfileState())
    val profileState = _profileStatus.asStateFlow()

    private var _token = MutableStateFlow(UserToken())

    private suspend fun getUserHobby(token: String): Result<UserHobby> =
        getUserHobbyUseCase(token)

    private suspend fun getToken(): Flow<String> = getTokenUseCase()

    fun setHobby() {
        viewModelScope.launch {
            val savedToken = getToken().first()
            _token.value = UserToken(savedToken)
            getUserHobby(
                _token.value.token
            ).onSuccess { response ->
                _profileStatus.update {
                    it.copy(
                        hobby = response.hobby
                    )
                }
            }
        }
    }
}

data class ProfileState(
    val hobby: String = "",
)