package com.pdmpa.stockmarketapp.presentation.signup

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.pdmpa.stockmarketapp.R
import com.pdmpa.stockmarketapp.ui.theme.Typography
import com.pdmpa.stockmarketapp.util.AuthUtil
import com.pdmpa.stockmarketapp.util.PrimaryButton
import com.pdmpa.stockmarketapp.util.StyledTextField

@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    var nameText by rememberSaveable { mutableStateOf("") }
    var emailText by rememberSaveable { mutableStateOf("") }
    var passwordText by rememberSaveable { mutableStateOf("") }
    var confirmPasswordText by rememberSaveable { mutableStateOf("") }

    var validateNameText by rememberSaveable { mutableStateOf(true) }
    var validateEmailText by rememberSaveable { mutableStateOf(true) }
    var validatePasswordText by rememberSaveable { mutableStateOf(true) }
    var validateConfirmPasswordText by rememberSaveable { mutableStateOf(true) }
    var validatePasswordsEqual by rememberSaveable { mutableStateOf(true) }
    var isPasswordsVisible by rememberSaveable { mutableStateOf(false) }
    var isConfirmPasswordsEqual by rememberSaveable { mutableStateOf(false) }

    fun validateData(
        name: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        val passwordRegex = AuthUtil.isPasswordValid(password)
        val confirmPasswordRegex = AuthUtil.isPasswordValid(confirmPassword)
        val emailRegex = AuthUtil.isEmailValid(email)

        validateNameText = name.isNotBlank()
        validateEmailText = emailRegex
        validatePasswordText = passwordRegex
        validateConfirmPasswordText = confirmPasswordRegex
        validatePasswordsEqual = password == confirmPassword

        return validateNameText && validateEmailText && validatePasswordText && validateConfirmPasswordText && validatePasswordsEqual
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.Transparent)
    ) {
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            Image(
                painter = painterResource(id = R.drawable.img_3),
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
                    ), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = stringResource(R.string.sign_up),
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .padding(top = 130.dp, start = 40.dp)
                        .fillMaxWidth(),
                    style = Typography.h5
                )
                Spacer(modifier = Modifier.height(8.dp))
                StyledTextField(
                    text = nameText,
                    placeholder = stringResource(R.string.name),
                    isPasswordTextField = false,
                    onValueChange = { nameText = it },
                    showError = !validateNameText,
                    errorMessage = stringResource(R.string.validate_name_error)
                )

                Spacer(modifier = Modifier.height(3.dp))
                StyledTextField(
                    text = emailText,
                    placeholder = stringResource(id = R.string.enter_email),
                    isPasswordTextField = false,
                    onValueChange = { emailText = it },
                    showError = !validateEmailText,
                    errorMessage = stringResource(id = R.string.validate_email_error)
                )

                Spacer(modifier = Modifier.height(3.dp))
                StyledTextField(
                    text = passwordText,
                    placeholder = stringResource(id = R.string.enter_password),
                    isPasswordTextField = true,
                    isPasswordVisible = isPasswordsVisible,
                    onVisibilityChange = { isPasswordsVisible = it },
                    showError = !validatePasswordText,
                    errorMessage = stringResource(id = R.string.validate_password_error),
                    onValueChange = { passwordText = it }
                )

                Spacer(modifier = Modifier.padding(3.dp))
                StyledTextField(
                    text = confirmPasswordText,
                    placeholder = stringResource(R.string.confirm_password),
                    isPasswordTextField = true,
                    isPasswordVisible = isConfirmPasswordsEqual,
                    onVisibilityChange = { isConfirmPasswordsEqual = it },
                    onValueChange = { confirmPasswordText = it },
                    showError = !validateConfirmPasswordText || !validatePasswordsEqual,
                    errorMessage = if (!validateConfirmPasswordText) stringResource(id = R.string.validate_password_error) else stringResource(
                        R.string.validate_equal_password_error
                    )
                )

                Spacer(modifier = Modifier.padding(10.dp))
                PrimaryButton(
                    text = stringResource(R.string.create_account),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp, end = 32.dp),
                    onClick = {
                        if (validateData(
                                nameText,
                                emailText,
                                passwordText,
                                confirmPasswordText
                            )
                        ) {
                            viewModel.onEvent(
                                SignUpEvent.SignUpClickAction(
                                    nameText,
                                    emailText,
                                    passwordText,
                                    navController
                                )
                            )
                        } else {
                            Log.d(ContentValues.TAG, "Error")
                        }
                    },
                    {}
                )

                Row(horizontalArrangement = Arrangement.Center) {
                    Text(
                        text = stringResource(R.string.joined_us_before),
                        modifier = Modifier.padding(top = 15.dp)
                    )
                    TextButton(
                        onClick = { viewModel.onEvent(SignUpEvent.LogInClickAction(navController)) },
                    ) {
                        Text(
                            text = "Login",
                            letterSpacing = 1.sp,
                            style = Typography.button
                        )
                    }
                }
            }
        }
    }

}