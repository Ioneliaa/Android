package com.pdmpa.stockmarketapp.util

import android.view.KeyEvent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.pdmpa.stockmarketapp.ui.theme.*


@Composable
fun PrimaryButton(
    text: String,
    modifier: Modifier,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = md_theme_light_secondaryContainer
        ),
        shape = RoundedCornerShape(topStart = 30.dp, bottomEnd = 30.dp),
    ) {
        Box(
            modifier = modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            content()
            Text(
                text = text,
                style = Typography.button,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(start = 4.dp),
                color = md_theme_light_primary
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun StyledTextField(
    text: String,
    placeholder: String,
    isPasswordTextField: Boolean = false,
    isPasswordVisible: Boolean = false,
    onVisibilityChange: (Boolean) -> Unit = {},
    onValueChange: (String) -> Unit,
    showError: Boolean = false,
    errorMessage: String = ""
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = text,
        onValueChange = { onValueChange(it) },
        shape = RoundedCornerShape(topEnd = 12.dp, bottomStart = 12.dp),
        label = {
            Text(
                text = placeholder,
                style = Typography.caption,
                color = md_theme_light_shadow
            )
        },
        placeholder = { Text(text = placeholder) },
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = md_theme_light_onPrimaryContainer,
            unfocusedBorderColor = md_theme_light_primary
        ),
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .onPreviewKeyEvent {
                if (it.key == Key.Enter && it.nativeKeyEvent.action == KeyEvent.ACTION_DOWN) {
                    focusManager.moveFocus(FocusDirection.Down)
                    true
                } else {
                    false
                }
            },
        isError = showError,
        trailingIcon = {
            if (isPasswordTextField) {
                IconButton(onClick = { onVisibilityChange(!isPasswordVisible) }) {
                    Icon(
                        painter = if (isPasswordVisible) painterResource(id = com.pdmpa.stockmarketapp.R.drawable.ic_baseline_visibility_24) else painterResource(
                            id = com.pdmpa.stockmarketapp.R.drawable.ic_baseline_visibility_off_24
                        ),
                        contentDescription = "Tootle password visibility"
                    )
                }
            }
        },
        visualTransformation = when {
            isPasswordTextField && isPasswordVisible -> VisualTransformation.None
            isPasswordTextField -> PasswordVisualTransformation()
            else -> VisualTransformation.None
        },
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            },
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
    )
    if (showError) {
        Text(
            text = errorMessage,
            color = Color.Red,
            style = Typography.caption,
            modifier = Modifier
                .padding(start = 10.dp)
                .offset(y = (-1).dp)
                .fillMaxWidth(0.9f)
        )
    }
}

