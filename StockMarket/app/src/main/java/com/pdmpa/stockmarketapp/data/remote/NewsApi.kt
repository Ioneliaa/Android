package com.pdmpa.stockmarketapp.data.remote

import com.pdmpa.stockmarketapp.domain.model.NewsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApi {

    @GET("v1/news/{filter}")
    suspend fun getNews(
        @Path("filter") filter: String,
        /**
         * available filters
         * 1. handpicked
         * 2. trending
         * 3. latest
         * 4. bullish
         * 5. bearish
         */
        @Query("limit") limit: Int = 20,
        @Query("skip") skip: Int = 0
    ): NewsDto
}