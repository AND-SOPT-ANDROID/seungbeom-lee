package org.sopt.and.core.designsystem.component.textfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R

@Composable
fun UserInfoTextField(
    textField: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isShown: Boolean,
    keyboardOptions: KeyboardOptions,
    trailingIcon: @Composable (() -> Unit)? = null,
    infoText: String? = null,
    infoIcon: @Composable (() -> Unit)? = {
        Icon(imageVector = Icons.Default.Info, contentDescription = Icons.Default.Info.name)
    }
) {
    val showPassword = remember(isShown) {
        if (!isShown) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }
    }
    val focusManager = LocalFocusManager.current

    Column {
        TextField(
            value = textField,
            onValueChange = onValueChange,
            trailingIcon = trailingIcon,
            placeholder = { Text(placeholder) },
            visualTransformation = showPassword,
            singleLine = true,
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus(true) },
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(R.color.textfield_gray2f)
            )
        )
        if (infoText != null) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                infoIcon?.invoke()
                Text(
                    text = infoText,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(7.dp),
                    color = colorResource(R.color.gray_a3)
                )
            }
        }
    }
}