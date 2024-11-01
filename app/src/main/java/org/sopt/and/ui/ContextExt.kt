package org.sopt.and.ui

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun Context.toast(@StringRes message: Int) {
    Toast.makeText(this, getString(message), Toast.LENGTH_SHORT).show()
}


fun SnackbarHostState.ShowSnackBar(
    scope: CoroutineScope,
    @StringRes message: Int,
    context: Context,
    duration: SnackbarDuration = SnackbarDuration.Short
): SnackbarResult? {
    var result: SnackbarResult? = null
    scope.launch {
        result = this@ShowSnackBar.showSnackbar(
            context.getString(message),
            duration = duration
        )
    }
    return result
}