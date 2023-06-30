package com.pdmpa.stockmarketapp.navigation.bottomNavigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.pdmpa.stockmarketapp.ui.theme.*

@Composable
fun BottomNavigationBar(
    navController: NavController,
    bottomBarState: MutableState<Boolean>
) {
    val items = listOf(
        BottomNavItem.HomeBottomNavItem,
        BottomNavItem.StocksListBottomNavItem,
        BottomNavItem.NewsBottomNavItem,
        BottomNavItem.ProfileBottomNavItem
    )
    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            NavigationBar(
                contentColor = Color.White,
                containerColor = md_theme_dark_onSecondary
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                items.forEach { item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title
                            )
                        },
                        label = {
                            Text(text = item.title)
                        },
                        selected = currentRoute == item.route,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = md_theme_dark_primaryContainer,
                            unselectedTextColor = Color.Gray,
                            selectedTextColor = Color.White,
                            unselectedIconColor = md_theme_dark_onPrimaryContainer,
                            indicatorColor = md_theme_light_inverseOnSurface

                        ),
                        onClick = {
                            navController.navigate(item.route)
                        })
                }
            }
        }
    )
}