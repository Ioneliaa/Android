package com.pdmpa.stockmarketapp.presentation.resetPassword

import androidx.navigation.NavController

sealed class ResetPasswordEvent {

    data class ResetClickAction(
        val email: String,
        val navController: NavController
    ) :
        ResetPasswordEvent()

    data class RegisterClickAction(
        val navController: NavController
    ) : ResetPasswordEvent()


}