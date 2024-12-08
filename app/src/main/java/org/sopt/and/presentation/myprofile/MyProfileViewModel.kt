package org.sopt.and.presentation.myprofile

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.sopt.and.domain.entity.UserToken
import org.sopt.and.domain.usecase.GetTokenUseCase
import org.sopt.and.domain.usecase.GetUserHobbyUseCase
import org.sopt.and.presentation.util.base.BaseViewModel
import org.sopt.and.presentation.util.view.LoadState
import javax.inject.Inject

@HiltViewModel
class MyProfileViewModel @Inject constructor(
    private val getUserHobby: GetUserHobbyUseCase,
    private val getToken: GetTokenUseCase
) : BaseViewModel<MyProfileContract.ProfileUiState, MyProfileContract.ProfileSideEffect, MyProfileContract.ProfileEvent>() {
    override fun createInitialState(): MyProfileContract.ProfileUiState =
        MyProfileContract.ProfileUiState()

    override suspend fun handleEvent(event: MyProfileContract.ProfileEvent) {
        when (event) {
            is MyProfileContract.ProfileEvent.FetchUserHobby ->
                setState { copy(loadState = event.loadState, hobby = event.userHobby) }

            is MyProfileContract.ProfileEvent.FetchToken ->
                setState { copy(token = event.token) }
        }
    }

    fun fetchToken() {
        viewModelScope.launch {
            setEvent(
                MyProfileContract.ProfileEvent.FetchToken(
                    token = UserToken(getToken().first())
                )
            )
        }
    }


    fun fetchUserHobby() {
        viewModelScope.launch {
            setEvent(
                MyProfileContract.ProfileEvent.FetchUserHobby(
                    loadState = LoadState.Loading,
                    userHobby = currentState.hobby
                )
            )

            getUserHobby(token = currentState.token.token)
                .onSuccess { response ->
                    setEvent(
                        MyProfileContract.ProfileEvent.FetchUserHobby(
                            loadState = LoadState.Success,
                            userHobby = response.hobby
                        )
                    )
                }
                .onFailure {
                    setEvent(
                        MyProfileContract.ProfileEvent.FetchUserHobby(
                            loadState = LoadState.Error,
                            userHobby = currentState.hobby
                        )
                    )
                }
        }
    }
}
