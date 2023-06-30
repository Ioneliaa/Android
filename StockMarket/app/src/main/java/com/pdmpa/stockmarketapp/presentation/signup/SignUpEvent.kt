package com.pdmpa.stockmarketapp.presentation.signup

import androidx.navigation.NavController

sealed class SignUpEvent {

    data class SignUpClickAction(
        val name: String,
        val email: String,
        val password: String,
        val navController: NavController
    ) : SignUpEvent()

    data class LogInClickAction(
        val navController: NavController
    ) : SignUpEvent()
}