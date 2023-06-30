package com.pdmpa.stockmarketapp.presentation.home

import androidx.lifecycle.ViewModel
import com.pdmpa.stockmarketapp.domain.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val authRepository: AuthenticationRepository) :
    ViewModel() {

    fun myGreetingMessage(): String {
        val cal = Calendar.getInstance()

        return when (cal.get(Calendar.HOUR_OF_DAY)) {
            in 0..11 -> "Good Morning!"
            in 12..15 -> "Good Afternoon!"
            in 16..20 -> "Good Evening!"
            in 21..23 -> "Good Night!"
            else -> "Hello"

        }
    }

    fun getUserName(): String? = authRepository.getUserName()
}