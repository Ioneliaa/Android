package com.pdmpa.stockmarketapp.presentation.listingscompany

import androidx.navigation.NavController

sealed class CompanyListingsEvent {
    object Refresh : CompanyListingsEvent()
    data class OnSearchQueryChange(val query: String) : CompanyListingsEvent()
    data class NavigateToInfo(val symbol: String, val navController: NavController) : CompanyListingsEvent()
}
