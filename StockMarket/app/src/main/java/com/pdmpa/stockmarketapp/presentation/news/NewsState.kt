package com.pdmpa.stockmarketapp.presentation.news

import com.pdmpa.stockmarketapp.domain.model.NewsDetail

data class NewsState(
    val isLoading: Boolean = false,
    val news: List<NewsDetail> = emptyList(),
    val error: String = ""
)
