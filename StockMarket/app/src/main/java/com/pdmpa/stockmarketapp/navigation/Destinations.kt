package com.pdmpa.stockmarketapp.navigation

interface Destinations {
    val route: String
}

object Splash : Destinations {
    override val route = "splash_screen"
}

object OnBoarding : Destinations {
    override val route = "on_boarding_screen"
}

object Login : Destinations {
    override val route = "login_screen"
}

object SignUp : Destinations {
    override val route = "signup_screen"
}

object ResetPassword : Destinations {
    override val route = "reset_password_screen"
}

object StocksList : Destinations {
    override val route = "stocks_list_screen"
}

object CompanyInfo : Destinations {
    override val route = "info_screen"
}

object Profile : Destinations {
    override val route = "profile_screen"
}

object News : Destinations {
    override val route = "news_screen"

}

object Home : Destinations {
    override val route = "home_screen"
}
object AddNewCard : Destinations {
    override val route = "add_new_card_screen"
}
object CardCreated : Destinations {
    override val route = "card_created_screen"
}
