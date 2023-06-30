package com.pdmpa.stockmarketapp.presentation.resetPassword

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
import com.pdmpa.stockmarketapp.ui.theme.md_theme_light_primary
import com.pdmpa.stockmarketapp.util.AuthUtil
import com.pdmpa.stockmarketapp.util.PrimaryButton
import com.pdmpa.stockmarketapp.util.StyledTextField

@Composable
fun ResetPasswordScreen(
    navController: NavController,
    viewModel: ResetPasswordViewModel = hiltViewModel()
) {

    var emailText by rememberSaveable { mutableStateOf("") }
    var validateEmailText by rememberSaveable { mutableStateOf(true) }

    fun validateData(
        email: String,
    ): Boolean {
        val emailRegex = AuthUtil.isEmailValid(email)
        validateEmailText = emailRegex

        return validateEmailText
    }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Color.Transparent)
    ) {
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            Image(
                painter = painterResource(id = R.drawable.img_13
                ),
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
                    text = stringResource(R.string.reset_password),
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .padding(top = 130.dp, start = 40.dp)
                        .fillMaxWidth(),
                    style = Typography.h5
                )

                Spacer(modifier = Modifier.height(50.dp))
                StyledTextField(
                    text = emailText,
                    placeholder = stringResource(R.string.enter_email),
                    isPasswordTextField = false,
                    onValueChange = { emailText = it },
                    showError = !validateEmailText,
                    errorMessage = stringResource(R.string.validate_email_error)
                )
                Spacer(modifier = Modifier.padding(3.dp))
                PrimaryButton(
                    text = stringResource(id = R.string.submit),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp, end = 32.dp),
                    onClick = {
                        if (validateData(emailText)) {
                            viewModel.onEvent(
                                ResetPasswordEvent.ResetClickAction(
                                    emailText,
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
                        onClick = {
                            viewModel.onEvent(
                                ResetPasswordEvent.RegisterClickAction(
                                    navController
                                )
                            )
                        },
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