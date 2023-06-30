package com.pdmpa.stockmarketapp.presentation.infocompany

import com.pdmpa.stockmarketapp.domain.model.CompanyInfo
import com.pdmpa.stockmarketapp.domain.model.IntradayInfo

data class CompanyInfoState(
    val stockInfos: List<IntradayInfo> = emptyList(),
    val company: CompanyInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)