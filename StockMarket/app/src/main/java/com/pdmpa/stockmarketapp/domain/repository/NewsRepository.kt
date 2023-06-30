package com.pdmpa.stockmarketapp.domain.repository

import com.pdmpa.stockmarketapp.domain.model.NewsDto

interface NewsRepository {
    suspend fun getNews(filter: String): NewsDto
}