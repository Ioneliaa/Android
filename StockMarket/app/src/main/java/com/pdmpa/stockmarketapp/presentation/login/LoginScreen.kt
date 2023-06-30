package com.pdmpa.stockmarketapp.presentation.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pdmpa.stockmarketapp.R
import com.pdmpa.stockmarketapp.ui.theme.Typography
import com.pdmpa.stockmarketapp.ui.theme.md_theme_light_primary
import com.pdmpa.stockmarketapp.util.AuthUtil
import com.pdmpa.stockmarketapp.util.PrimaryButton
import com.pdmpa.stockmarketapp.util.StyledTextField


@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        viewModel.toastMessage.collect {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    var emailText by rememberSaveable { mutableStateOf("") }
    var passwordText by rememberSaveable { mutableStateOf("") }
    var isPasswordsVisible by rememberSaveable { mutableStateOf(false) }

    var validateEmailText by rememberSaveable { mutableStateOf(true) }
    var validatePasswordText by rememberSaveable { mutableStateOf(true) }

    fun validateData(
        email: String,
        password: String,
    ): Boolean {
        val passwordRegex = AuthUtil.isPasswordValid(password)
        val emailRegex = AuthUtil.isEmailValid(email)

        validateEmailText = emailRegex
        validatePasswordText = passwordRegex

        return validateEmailText && validatePasswordText
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.Transparent)
    ) {


        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            Image(
                painter = painterResource(id = R.drawable.img),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .verticalScroll(
                        rememberScrollState()
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    text = stringResource(R.string.login),
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .padding(top = 130.dp, start = 40.dp)
                        .fillMaxWidth(),
                    style = Typography.h5
                )

                Spacer(modifier = Modifier.height(8.dp))
                StyledTextField(
                    text = emailText,
                    placeholder = stringResource(R.string.enter_email),
                    isPasswordTextField = false,
                    onValueChange = { emailText = it },
                    showError = !validateEmailText,
                    errorMessage = stringResource(R.string.validate_email_error)
                )

                Spacer(modifier = Modifier.padding(3.dp))
                StyledTextField(
                    text = passwordText,
                    placeholder = stringResource(R.string.enter_password),
                    isPasswordTextField = true,
                    isPasswordVisible = isPasswordsVisible,
                    onVisibilityChange = { isPasswordsVisible = it },
                    onValueChange = { passwordText = it },
                    showError = !validatePasswordText,
                    errorMessage = stringResource(R.string.validate_password_error)
                )

                TextButton(
                    onClick = {
                        viewModel.onEvent(LogInEvent.ResetClickAction(navController))
                    },
                    modifier = Modifier
                        .align(
                            alignment = Alignment.End
                        )
                        .padding(horizontal = 16.dp)

                ) {
                    Text(
                        text = stringResource(R.string.forgot_password),
                        letterSpacing = 1.sp,
                        style = Typography.button,
                        color = md_theme_light_primary
                    )
                }
                PrimaryButton(
                    text = stringResource(id = R.string.login),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp, end = 32.dp),
                    onClick = {
                        if (validateData(emailText, passwordText)) {
                            viewModel.onEvent(
                                LogInEvent.LogInClickAction(
                                    emailText,
                                    passwordText,
                                    navController
                                )
                            )
                        }
                    },
                    {}
                )

                Row(horizontalArrangement = Arrangement.Center) {
                    Text(
                        text = stringResource(R.string.new_to_app),
                        modifier = Modifier.padding(top = 14.dp),
                        fontSize = 12.sp
                    )
                    TextButton(
                        onClick = { viewModel.onEvent(LogInEvent.RegisterClickAction(navController)) },
                    ) {
                        Text(
                            text = stringResource(R.string.register),
                            letterSpacing = 1.sp,
                            style = Typography.button,
                            color = md_theme_light_primary
                        )
                    }
                }
            }
        }
    }
}
