package org.sopt.and.component

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R

@Composable
fun SignUpTextField(
    text: String,
    textField: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isShown: Boolean,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    trailingIcon: @Composable (() -> Unit) = { }
) {
    val showPassword = remember(isShown) {
        if (!isShown) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }
    }
    val focusManager = LocalFocusManager.current
    Column(modifier = Modifier) {
        TextField(
            value = textField,
            onValueChange = onValueChange,
            trailingIcon = trailingIcon,
            placeholder = { Text(placeholder) },
            visualTransformation = showPassword,
            singleLine = true,
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus(true)
                },
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }),
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(R.color.textfield_gray2f)
            ),
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = Icons.Default.Info.name
            )
            Text(
                text = text,
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(7.dp), color = colorResource(R.color.gray_a3)
            )
        }
    }
}

@Composable
fun LogInTextField(
    textField: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isShown: Boolean,
    keyboardOptions: KeyboardOptions,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    val showPassword = remember(isShown) {
        if (!isShown) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }
    }
    val focusManager = LocalFocusManager.current

    Column(modifier = Modifier) {
        TextField(
            value = textField,
            onValueChange = onValueChange,
            trailingIcon = trailingIcon,
            placeholder = { Text(placeholder) },
            visualTransformation = showPassword,
            singleLine = true,
            keyboardOptions = keyboardOptions, keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus(true)
                },
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }),
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(R.color.textfield_gray2f)
            )
        )
    }
}

@Composable
fun TextAndContent(text: String, content: @Composable () -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Text(
            text = text,
            color = colorResource(R.color.white),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        content()
    }
}