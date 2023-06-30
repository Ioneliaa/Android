package com.pdmpa.stockmarketapp.domain.repository

import com.pdmpa.stockmarketapp.domain.model.CompanyInfo
import com.pdmpa.stockmarketapp.domain.model.CompanyListing
import com.pdmpa.stockmarketapp.domain.model.IntradayInfo
import com.pdmpa.stockmarketapp.util.Resources
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resources<List<CompanyListing>>>

    suspend fun getIntradayInfo(
        symbol: String
    ): Resources<List<IntradayInfo>>

    suspend fun getCompanyInfo(
        symbol: String
    ): Resources<CompanyInfo>
}