package org.sopt.and.presentation.myprofile

import org.sopt.and.domain.entity.UserToken
import org.sopt.and.presentation.util.base.UiEvent
import org.sopt.and.presentation.util.base.UiSideEffect
import org.sopt.and.presentation.util.base.UiState
import org.sopt.and.presentation.util.view.LoadState

class MyProfileContract {
    data class ProfileUiState(
        val hobby: String = "",
        val loadState: LoadState = LoadState.Idle,
        val token: UserToken = UserToken()
    ) : UiState

    sealed interface ProfileSideEffect : UiSideEffect

    sealed class ProfileEvent : UiEvent {
        data class FetchUserHobby(val loadState: LoadState, val userHobby: String) : ProfileEvent()
        data class FetchToken(val token: UserToken) : ProfileEvent()
    }
}