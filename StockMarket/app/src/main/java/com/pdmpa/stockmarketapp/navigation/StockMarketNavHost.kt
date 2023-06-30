package com.pdmpa.stockmarketapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pdmpa.stockmarketapp.presentation.home.HomeScreen
import com.pdmpa.stockmarketapp.presentation.home.card_completed.CardAddedSuccessfully
import com.pdmpa.stockmarketapp.presentation.home.new_card.AddNewCardScreen
import com.pdmpa.stockmarketapp.presentation.infocompany.CompanyInfoScreen
import com.pdmpa.stockmarketapp.presentation.listingscompany.CompanyListingsScreen
import com.pdmpa.stockmarketapp.presentation.login.LoginScreen
import com.pdmpa.stockmarketapp.presentation.news.NewsScreen
import com.pdmpa.stockmarketapp.presentation.onBoarding.OnBoardingScreen
import com.pdmpa.stockmarketapp.presentation.profile.ProfileScreen
import com.pdmpa.stockmarketapp.presentation.resetPassword.ResetPasswordScreen
import com.pdmpa.stockmarketapp.presentation.signup.SignUpScreen
import com.pdmpa.stockmarketapp.presentation.splash_screen.SplashScreen

@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun StockMarketNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Splash.route,
        modifier = modifier
    ) {
        composable(route = Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = OnBoarding.route) {
            OnBoardingScreen(navController = navController)
        }
        composable(route = Login.route) {
            LoginScreen(navController = navController)
        }
        composable(route = SignUp.route) {
            SignUpScreen(navController = navController)
        }
        composable(route = ResetPassword.route) {
            ResetPasswordScreen(navController = navController)
        }
        composable(route = StocksList.route) {
            CompanyListingsScreen(navController = navController)
        }
        composable(
            route = CompanyInfo.route + "/{symbol}",
            arguments = listOf(navArgument("symbol") { type = NavType.StringType })
        ) {
            CompanyInfoScreen(navController = navController)
        }
        composable(route = Profile.route) {
            ProfileScreen(navController = navController)
        }
        composable(route = News.route) {
            NewsScreen()
        }
        composable(route = Home.route) {
            HomeScreen(navController = navController)
        }
        composable(route = AddNewCard.route) {
            AddNewCardScreen(navController = navController)
        }
        composable(route = CardCreated.route) {
            CardAddedSuccessfully(navController = navController)
        }
    }
}