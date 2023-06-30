package com.pdmpa.stockmarketapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pdmpa.stockmarketapp.navigation.StockMarketNavHost
import com.pdmpa.stockmarketapp.navigation.bottomNavigation.BottomNavigationBar
import com.pdmpa.stockmarketapp.ui.theme.StockMarketAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockMarketApp()
        }
    }
}


@Composable
fun StockMarketApp() {
    val bottomBarState = rememberSaveable { (mutableStateOf(false)) }
    StockMarketAppTheme {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        when (navBackStackEntry?.destination?.route) {
            "home_screen" -> {
                bottomBarState.value = true
            }
            "profile_screen" -> {
                bottomBarState.value = true
            }
            "news_screen" -> {
                bottomBarState.value = true
            }
            "stocks_list_screen" -> {
                bottomBarState.value = true
            }
            "login_screen" -> {
                bottomBarState.value = false
            }
            "signup_screen" -> {
                bottomBarState.value = false
            }
            "splash_screen" -> {
                bottomBarState.value = false
            }
            "add_new_card_screen" -> {
                bottomBarState.value = false
            }
            "card_created_screen" -> {
                bottomBarState.value = false
            }

        }
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            backgroundColor = MaterialTheme.colors.background,
            bottomBar = {
                BottomNavigationBar(navController, bottomBarState)
            }
        ) { innerPadding ->
            StockMarketNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )

        }
    }
}