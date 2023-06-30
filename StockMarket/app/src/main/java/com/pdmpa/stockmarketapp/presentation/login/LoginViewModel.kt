package com.pdmpa.stockmarketapp.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdmpa.stockmarketapp.domain.repository.AuthStatus
import com.pdmpa.stockmarketapp.domain.repository.AuthenticationRepository
import com.pdmpa.stockmarketapp.navigation.Home
import com.pdmpa.stockmarketapp.navigation.ResetPassword
import com.pdmpa.stockmarketapp.navigation.SignUp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthenticationRepository,
) : ViewModel() {
    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()

    fun onEvent(event: LogInEvent) {
        when (event) {
            is LogInEvent.LogInClickAction -> {
                viewModelScope.launch {
                    repository.logInWithEmail(event.email, event.password)
                        .collect {
                            when (it) {
                                is AuthStatus.Failure -> {
                                    _toastMessage.emit("Email or password incorrect")
                                }
                                AuthStatus.Success -> {
                                    _toastMessage.emit("Login successfully")
                                    event.navController.navigate(Home.route)
                                }
                            }
                        }
                }
            }
            is LogInEvent.RegisterClickAction -> {
                event.navController.navigate(SignUp.route)
            }
            is LogInEvent.ResetClickAction ->
                event.navController.navigate(ResetPassword.route)

        }
    }
}