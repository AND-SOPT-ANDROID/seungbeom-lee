package org.sopt.and.presentation.myprofile

import android.util.Log
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
import org.sopt.and.domain.usecase.DataStoreGetTokenUseCase
import org.sopt.and.domain.usecase.GetUserHobbyUseCase
import javax.inject.Inject

@HiltViewModel
class MyProfileViewModel @Inject constructor(
    private val getUserHobbyUseCase: GetUserHobbyUseCase,
    private val dataStoreGetTokenUseCase: DataStoreGetTokenUseCase
) : ViewModel() {
    private val _profileStatus = MutableStateFlow(ProfileState())
    val profileState = _profileStatus.asStateFlow()

    private var _token = MutableStateFlow(UserToken())


    init {
        viewModelScope.launch {
            val savedToken = getToken().first()
            _token.value = UserToken(savedToken)
            getUserHobby(
                _token.value.token
            ).onFailure { exception ->
                Log.d("ServerExeception", "취미 조회 실패: ${exception.message}")
            }.onSuccess { response ->
                _profileStatus.update {
                    it.copy(
                        hobby = response.hobby
                    )
                }
            }


        }
    }

    private suspend fun getUserHobby(token: String): Result<UserHobby> =
        getUserHobbyUseCase.invoke(token)


    private suspend fun getToken(): Flow<String> = dataStoreGetTokenUseCase.invoke()

}

data class ProfileState(
    val hobby: String = "",
)