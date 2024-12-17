package org.sopt.and.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.sopt.and.R
import org.sopt.and.presentation.signup.SignUpContract
import org.sopt.and.presentation.util.base.BaseViewModel

class HomeViewModel :
    BaseViewModel<HomeContract.HomeUIState, HomeContract.HomeSideEffect, HomeContract.HomeEvent>() {
    override fun createInitialState(): HomeContract.HomeUIState = HomeContract.HomeUIState(
        genreList = getGenreTextList(),
        editorRecommendList = getEditorRecommendList(),
        top20List = getTop20List(),
        mainBannerList = getMainBannerImage()
    )

    override suspend fun handleEvent(event: HomeContract.HomeEvent) {
    }

    private fun getGenreTextList() = listOf(
        "뉴클래식", "드라마", "예능", "영화", "애니", "해외시리즈"
    )

    private fun getMainBannerImage() = listOf(
        R.drawable.img_banner_1,
        R.drawable.img_banner_3,
        R.drawable.img_banner_4,
        R.drawable.img_banner_5,
        R.drawable.img_banner_6,
        R.drawable.img_banner_7
    )

    private fun getEditorRecommendList() = listOf(
        R.drawable.img_editor_recommed_1,
        R.drawable.img_editor_recommed_2,
        R.drawable.img_editor_recommed_3,
        R.drawable.img_editor_recommed_4,
        R.drawable.img_editor_recommed_5,
        R.drawable.img_editor_recommed_6
    )

    private fun getTop20List() = listOf(
        R.drawable.img_top20_1,
        R.drawable.img_top20_2,
        R.drawable.img_top20_3,
        R.drawable.img_top20_4,
        R.drawable.img_top20_5,
        R.drawable.img_top20_6,
        R.drawable.img_top20_7,
        R.drawable.img_top20_8,
        R.drawable.img_top20_9,
        R.drawable.img_top20_10,
    )
}

