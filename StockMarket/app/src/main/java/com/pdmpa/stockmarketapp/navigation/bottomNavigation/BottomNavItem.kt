package com.pdmpa.stockmarketapp.navigation.bottomNavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Equalizer
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object HomeBottomNavItem : BottomNavItem(
        route = Home.route,
        title = "Home",
        icon = Icons.Default.Home
    )

    object ProfileBottomNavItem : BottomNavItem(
        route = Profile.route,
        title = "Profile",
        icon = Icons.Default.Person
    )

    object NewsBottomNavItem : BottomNavItem(
        route = News.route,
        title = "News",
        icon = Icons.Default.Newspaper
    )

    object StocksListBottomNavItem : BottomNavItem(
        route = StocksList.route,
        title = "Market",
        icon = Icons.Default.Equalizer
    )
}