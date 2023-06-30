package com.pdmpa.stockmarketapp.data.repository

import com.pdmpa.stockmarketapp.data.csv.CSVParser
import com.pdmpa.stockmarketapp.data.local.StockDatabase
import com.pdmpa.stockmarketapp.data.mapper.toCompanyInfo
import com.pdmpa.stockmarketapp.data.mapper.toCompanyListing
import com.pdmpa.stockmarketapp.data.mapper.toCompanyListingEntity
import com.pdmpa.stockmarketapp.data.remote.StockApi
import com.pdmpa.stockmarketapp.domain.model.CompanyInfo
import com.pdmpa.stockmarketapp.domain.model.CompanyListing
import com.pdmpa.stockmarketapp.domain.model.IntradayInfo
import com.pdmpa.stockmarketapp.domain.repository.StockRepository
import com.pdmpa.stockmarketapp.util.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val api: StockApi,
    db: StockDatabase,
    private val companyListingParser: CSVParser<CompanyListing>,
    private val intradayInfoParser: CSVParser<IntradayInfo>
) : StockRepository {

    private val dao = db.dao

    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resources<List<CompanyListing>>> {
        return flow {
            emit(Resources.Loading(true))
            val localListings = dao.searchCompanyListing(query)
            emit(Resources.Success(
                data = localListings.map { it.toCompanyListing() }
            ))

            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote

            if (shouldJustLoadFromCache) {
                emit(Resources.Loading(false))
                return@flow
            }

            val listRemoteListings = try {
                val response = api.getListings()
                companyListingParser.parse(response.byteStream())

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resources.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resources.Error("Couldn't load data"))
                null
            }

            listRemoteListings?.let { listings ->
                dao.clearCompanyListings()
                dao.insertCompanyListings(
                    listings.map { it.toCompanyListingEntity() }
                )
                emit(Resources.Success(
                    data = dao.searchCompanyListing("").map { it.toCompanyListing() }
                ))
                emit(Resources.Loading(false))
            }
        }
    }

    override suspend fun getIntradayInfo(symbol: String): Resources<List<IntradayInfo>> {
        return try {
            val response = api.getIntradayInfo(symbol)
            val results = intradayInfoParser.parse(response.byteStream())
            Resources.Success(results)
        } catch (e: IOException) {
            e.printStackTrace()
            Resources.Error(
                message = "Couldn't load intraday info"
            )
        } catch (e: HttpException) {
            e.printStackTrace()
            Resources.Error(
                message = "Couldn't load intraday info"
            )
        }
    }

    override suspend fun getCompanyInfo(symbol: String): Resources<CompanyInfo> {
        return try {
            val result = api.getCompanyInfo(symbol)
            Resources.Success(result.toCompanyInfo())
        } catch (e: IOException) {
            e.printStackTrace()
            Resources.Error(
                message = "Couldn't load company info"
            )
        } catch (e: HttpException) {
            e.printStackTrace()
            Resources.Error(
                message = "Couldn't load company info"
            )
        }
    }
}