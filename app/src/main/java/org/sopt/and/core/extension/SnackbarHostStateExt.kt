package org.sopt.and.core.extension

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult

suspend fun SnackbarHostState.showsnackBar(
    @StringRes message: Int,
    context: Context,
    duration: SnackbarDuration = SnackbarDuration.Short
): SnackbarResult {
    val result = this@showsnackBar.showSnackbar(
        context.getString(message),
        duration = duration
    )

    return result
}