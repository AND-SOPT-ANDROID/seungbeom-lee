package org.sopt.and.feature.home.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.sopt.and.R

@Composable
fun MainBannerPager(bannerList: List<Int>) {
    val totalPageNum = bannerList.size
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { Int.MAX_VALUE },
    )
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp


    LaunchedEffect(true) {
        while (true) {
            delay(2000)
            if (!pagerState.isScrollInProgress) {
                val nextPage = (pagerState.currentPage + 1) % (totalPageNum)
                Log.d("Zz", "${pagerState.currentPage}")
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    HorizontalPager(
        modifier = Modifier
            .fillMaxWidth(),
        state = pagerState,
        contentPadding = PaddingValues(horizontal = screenWidth * 0.1f / 2),
        pageSpacing = 10.dp,
    ) { page ->
        val banner = bannerList[page % totalPageNum]
        Banner(bannerImg = banner, index = page, totalSize = totalPageNum)
    }
}

@Composable
fun Banner(bannerImg: Int, index: Int, totalSize: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = colorResource(R.color.basic_background))
    ) {
        Image(
            painter = painterResource(id = bannerImg),
            contentDescription = "Banner Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(10.dp)
                .clip(RoundedCornerShape(50))
                .background(Color.Black)
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Row {
                Text(
                    text = "${index + 1} / ",
                    color = Color.White,
                    fontSize = 12.sp,
                )
                Text(
                    text = "$totalSize",
                    color = Color.Gray,
                    fontSize = 12.sp,
                )
            }
        }
    }
}