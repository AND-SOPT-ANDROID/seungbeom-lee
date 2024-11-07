package org.sopt.and.presentation.main.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.presentation.main.MainTab


@Composable
fun MainBottomTabsBar(
    mainTabs: List<MainTab>,
    currentBottomTab: MainTab?,
    onTabClicked: (MainTab) -> Unit,
) {
    NavigationBar(
        containerColor = colorResource(R.color.basic_background)
    ) {
        mainTabs.forEach { tab ->
            NavigationBarItem(
                selected = currentBottomTab == tab,
                onClick = { onTabClicked(tab) },
                icon = {
                    Icon(
                        imageVector = tab.iconImageVector,
                        contentDescription = stringResource(tab.contentDescription) ,
                        modifier = Modifier
                            .size(24.dp),
                    )
                },
                label = {
                    Text(
                        text = stringResource(tab.contentDescription),
                        color = Color.White,
                        fontSize = 10.sp,
                    )
                },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults
                    .colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = Color.White,
                        unselectedIconColor = Color.LightGray,
                        unselectedTextColor = Color.LightGray,
                        indicatorColor = Color.Transparent
                    )
            )
        }
    }
}