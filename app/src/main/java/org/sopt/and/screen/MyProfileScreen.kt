package org.sopt.and.screen

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.component.TextAndContent

@Composable
fun MyProfileScreen(intent : Intent?, modifier: Modifier = Modifier) {
    val name = intent?.getStringExtra("profileid") ?: ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.basic_background))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(R.color.profile_gray_25))
                .padding(10.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = Icons.Default.AccountCircle.name,
                    modifier = modifier
                        .size(70.dp)
                        .padding()
                )
                Text(
                    text = name,
                    color = colorResource(R.color.white),
                    modifier = modifier
                        .padding(start = 5.dp)
                        .weight(1f)
                )
                Spacer(modifier.padding(10.dp))
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = Icons.Default.Notifications.name,
                    tint = colorResource(R.color.white)
                )
                Spacer(modifier.padding(10.dp))
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = Icons.Default.Settings.name,
                    tint = colorResource(R.color.white)
                )
            }

            Spacer(modifier.padding(10.dp))
            Column {
                Text(
                    text = "첫 결제시 첫 달 100원!",
                    color = colorResource(R.color.gray_63),
                    modifier = modifier.padding(bottom = 5.dp)
                )
                Text(
                    text = "구매하기  >",
                    color = colorResource(R.color.white)
                )

            }

            Spacer(modifier.padding(10.dp))
            Spacer(
                modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(colorResource(R.color.black))
            )
            Column {
                Text(
                    text = "현재 보유하신 이용권이 없습니다.",
                    color = colorResource(R.color.gray_63),
                    modifier = modifier.padding(top = 5.dp, bottom = 5.dp)

                )
                Text(
                    text = "구매하기  >",
                    color = colorResource(R.color.white),
                    modifier = modifier.padding(bottom = 10.dp)

                )
            }
        }
        TextAndContent("전체 시청 내역") {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.fillMaxWidth().height(225.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = Icons.Default.Info.name,
                    modifier = modifier.size(60.dp),
                    tint = colorResource(R.color.gray_a3)
                )
                Spacer(modifier.padding(5.dp))
                Text(
                    "시청내역이 없어요",
                    color = colorResource(R.color.gray_a3)
                )
            }
        }
        TextAndContent("관심 프로그램") {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.fillMaxWidth().height(225.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = Icons.Default.Info.name,
                    modifier = modifier.size(60.dp),
                    tint = colorResource(R.color.gray_a3)
                )
                Spacer(modifier.padding(5.dp))
                Text(
                    "관심프로그램이 없어요",
                    color = colorResource(R.color.gray_a3)
                )
            }
        }
    }
}

