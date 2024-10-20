package org.sopt.and.screen

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.serialization.Serializable
import org.sopt.and.R
import org.sopt.and.component.TextAndContent

@Serializable
data class MyPage(
    val email: String
)


@Composable
fun MyProfileScreen(email: String) {
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
                    modifier = Modifier
                        .size(70.dp)
                        .padding()
                )
                Text(
                    text = email,
                    color = colorResource(R.color.white),
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .weight(1f)
                )
                Spacer(Modifier.padding(10.dp))
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

            Spacer(Modifier.padding(10.dp))
            Column {
                Text(
                    text = stringResource(R.string.first_month_pay100),
                    color = colorResource(R.color.gray_63),
                    modifier = Modifier.padding(bottom = 5.dp)
                )
                Text(
                    text = stringResource(R.string.purchase_mypage),
                    color = colorResource(R.color.white)
                )
            }

            Spacer(Modifier.padding(10.dp))
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(colorResource(R.color.black))
            )
            Column {
                Text(
                    text = stringResource(R.string.no_ticket_mypage),
                    color = colorResource(R.color.gray_63),
                    modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)

                )
                Text(
                    text = stringResource(R.string.purchase_mypage),
                    color = colorResource(R.color.white),
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }
        }
        TextAndContent(text = stringResource(R.string.viewing_history_mypage)) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(225.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = Icons.Default.Info.name,
                    modifier = Modifier.size(60.dp),
                    tint = colorResource(R.color.gray_a3)
                )
                Spacer(Modifier.padding(5.dp))
                Text(
                    text = stringResource(R.string.no_viewing_histoory_mypage),
                    color = colorResource(R.color.gray_a3)
                )
            }
        }
        TextAndContent(stringResource(R.string.like_program_mypage)) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(225.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = Icons.Default.Info.name,
                    modifier = Modifier.size(60.dp),
                    tint = colorResource(R.color.gray_a3)
                )
                Spacer(Modifier.padding(5.dp))
                Text(
                    text = stringResource(R.string.no_like_program_mypage),
                    color = colorResource(R.color.gray_a3)
                )
            }
        }
    }
}

