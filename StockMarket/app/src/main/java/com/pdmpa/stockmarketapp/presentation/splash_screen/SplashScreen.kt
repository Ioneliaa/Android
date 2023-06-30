package com.pdmpa.stockmarketapp.presentation.splash_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.pdmpa.stockmarketapp.R
import com.pdmpa.stockmarketapp.navigation.Login
import com.pdmpa.stockmarketapp.navigation.OnBoarding
import com.pdmpa.stockmarketapp.presentation.onBoarding.DataStoreRepository
import com.pdmpa.stockmarketapp.presentation.onBoarding.DataStoreRepository.PreferencesKey.dataStore
import com.pdmpa.stockmarketapp.ui.theme.md_theme_light_background
import kotlinx.coroutines.async


@SuppressLint("CoroutineCreationDuringComposition")
@Composable

fun SplashScreen(navController: NavHostController) {
    val scope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(md_theme_light_background)
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.stocks))
        val logoAnimationState =
            animateLottieCompositionAsState(composition = composition)
        LottieAnimation(
            composition = composition,
            progress = { logoAnimationState.progress }
        )
        val dataStoreRepository = DataStoreRepository(LocalContext.current.dataStore)
        if (logoAnimationState.isAtEnd && logoAnimationState.isPlaying) {
            scope.async {
                dataStoreRepository.readOnBoardingState().collect {
                    if (it) {
                        navController.navigate(Login.route)
                    } else {
                        navController.navigate(OnBoarding.route)
                    }
                }
            }
        }
    }
}
