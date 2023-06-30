package com.pdmpa.stockmarketapp.presentation.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdmpa.stockmarketapp.domain.repository.AuthStatus
import com.pdmpa.stockmarketapp.domain.repository.AuthenticationRepository
import com.pdmpa.stockmarketapp.navigation.Login
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AuthenticationRepository,
) : ViewModel() {
    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.LogInClickAction -> {
                event.navController.navigate(Login.route)
            }
            is SignUpEvent.SignUpClickAction -> {
                viewModelScope.launch {
                    repository.signUpWithEmail(event.email, event.password, event.name).collect {
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