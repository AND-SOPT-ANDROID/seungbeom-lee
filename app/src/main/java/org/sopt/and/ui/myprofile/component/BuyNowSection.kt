package org.sopt.and.ui.myprofile.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.sopt.and.R

@Composable
fun BuyNowSection(
    text: String,
) {
    Column {
        Text(
            text = text,
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