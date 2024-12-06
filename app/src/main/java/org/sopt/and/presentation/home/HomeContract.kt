package org.sopt.and.presentation.home

import org.sopt.and.presentation.util.base.UiEvent
import org.sopt.and.presentation.util.base.UiSideEffect
import org.sopt.and.presentation.util.base.UiState

class HomeContract {
    data class HomeUIState(
        val genreList: List<String> = listOf(),
        val mainBannerList: List<Int> = listOf(),
        val editorRecommendList: List<Int> = listOf(),
        val top20List: List<Int> = listOf()
    ) : UiState

    sealed interface HomeSideEffect : UiSideEffect

    sealed class HomeEvent : UiEvent
}