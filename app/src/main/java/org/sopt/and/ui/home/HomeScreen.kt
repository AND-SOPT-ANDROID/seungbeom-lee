package org.sopt.and.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.serialization.Serializable
import org.sopt.and.R


@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val homeViewModel = viewModel<HomeViewModel>()

    val uiState = homeViewModel.uiState
    val genreList = uiState.value.genreList
    val bannerList = uiState.value.mainBannerList
    val editorRecommendList = uiState.value.editorRecomendList
    val top20List = uiState.value.top20List

    val scrollState = rememberScrollState()
    val pagerState = rememberPagerState(pageCount = { bannerList.size })
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.basic_background))
            .padding(20.dp)
            .verticalScroll(state = scrollState)
    ) {
        Row(
            modifier = Modifier.heightIn(min = 60.dp, max = 80.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.img_main_logo),
                contentDescription = Icons.Default.AccountCircle.name,
                modifier = Modifier
                    .width(110.dp)
                    .height(30.dp)
            )

            Spacer(Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = Icons.Default.Notifications.name,
                tint = colorResource(R.color.white)
            )
            Spacer(Modifier.padding(10.dp))
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = Icons.Default.Settings.name,
                tint = colorResource(R.color.white)
            )
        }
        Spacer(Modifier.height(30.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            genreList.forEach { genre ->
                Text(
                    text = genre,
                    color = colorResource(R.color.gray_a3),
                    fontSize = 16.sp
                )
            }
        }
        Spacer(Modifier.height(20.dp))

        MainBannerPager(bannerList, pagerState)

        Spacer(Modifier.height(20.dp))

        RecommendedContent(stringResource(R.string.home_editor_recommend_content), true) {
            ContentLazyList(editorRecommendList)
        }

        Spacer(Modifier.height(20.dp))

        RecommendedContent(stringResource(R.string.home_today_top20), false) {
            Top20LazyList(top20List)
        }
    }
}

@Composable
fun RecommendedContent(
    text: String,
    isIconVisible: Boolean,
    content: @Composable () -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            if (isIconVisible) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = Icons.AutoMirrored.Filled.KeyboardArrowRight.name,
                    tint = Color.Gray
                )
            }
        }
        content()
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}

