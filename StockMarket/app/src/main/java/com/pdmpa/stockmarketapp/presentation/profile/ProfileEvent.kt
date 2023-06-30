package com.pdmpa.stockmarketapp.presentation.profile

import androidx.navigation.NavController

sealed class ProfileEvent {
    data class SignOutClickAction(
        val navController: NavController
    ) : ProfileEvent()
}
