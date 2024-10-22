package org.sopt.and.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.sopt.and.R

class HomeViewModel : ViewModel() {
    private val genreTextList = listOf(
        "뉴클래식", "드라마", "예능", "영화", "애니", "해외시리즈"
    )
    private val mainBannerImage = listOf(
        R.drawable.img_banner_1,
        R.drawable.img_banner_3,
        R.drawable.img_banner_4,
        R.drawable.img_banner_5,
        R.drawable.img_banner_6,
        R.drawable.img_banner_7
    )
    private val editorRecommendList = listOf(
        R.drawable.img_editor_recommed_1,
        R.drawable.img_editor_recommed_2,
        R.drawable.img_editor_recommed_3,
        R.drawable.img_editor_recommed_4,
        R.drawable.img_editor_recommed_5,
        R.drawable.img_editor_recommed_6
    )
    private val top20List = listOf(
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

    private val _uiState =
        mutableStateOf(UIState(genreTextList, mainBannerImage, editorRecommendList, top20List))
    val uiState = _uiState

}

data class UIState(
    val genreList: List<String> = listOf(),
    val mainBannerList: List<Int> = listOf(),
    val editorRecomendList: List<Int> = listOf(),
    val top20List: List<Int> = listOf()
)