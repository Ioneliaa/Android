package com.pdmpa.stockmarketapp.presentation.resetPassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdmpa.stockmarketapp.domain.repository.AuthStatus
import com.pdmpa.stockmarketapp.domain.repository.AuthenticationRepository
import com.pdmpa.stockmarketapp.navigation.Login
import com.pdmpa.stockmarketapp.navigation.SignUp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val repository: AuthenticationRepository,
) : ViewModel() {
    fun onEvent(event: ResetPasswordEvent) {
        when (event) {
            is ResetPasswordEvent.RegisterClickAction -> {
                event.navController.navigate(SignUp.route)
            }
            is ResetPasswordEvent.ResetClickAction -> {
                viewModelScope.launch {
                    repository.resetPasswordWithEmail(event.email)
                        .collect {
                            when (it) {
                                is AuthStatus.Failure -> Unit
                                AuthStatus.Success -> {
                                    event.navController.navigate(Login.route)
                                }
                            }
                        }
                }
            }
        }

    }
}