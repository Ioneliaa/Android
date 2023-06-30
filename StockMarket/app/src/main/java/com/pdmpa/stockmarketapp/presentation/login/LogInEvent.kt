package com.pdmpa.stockmarketapp.presentation.login

import androidx.navigation.NavController

sealed class LogInEvent {

    data class LogInClickAction(
        val email: String,
        val password: String,
        val navController: NavController
    ) :
        LogInEvent()

    data class RegisterClickAction(
        val navController: NavController
    ) : LogInEvent()

    data class ResetClickAction(
        val navController: NavController
    ) : LogInEvent()

}