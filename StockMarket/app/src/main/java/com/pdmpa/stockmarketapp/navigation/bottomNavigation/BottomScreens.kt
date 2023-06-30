package com.pdmpa.stockmarketapp.navigation.bottomNavigation

import com.pdmpa.stockmarketapp.navigation.Destinations

object Home : Destinations {
    override val route: String = "home_screen"

}
object Profile : Destinations {
    override val route: String = "profile_screen"

}

object StocksList : Destinations {
    override val route: String = "stocks_list_screen"

}

object News : Destinations {
    override val route: String = "news_screen"
}
