package com.pdmpa.stockmarketapp.data.mapper

import com.pdmpa.stockmarketapp.data.local.CompanyListingEntity
import com.pdmpa.stockmarketapp.data.remote.dto.CompanyInfoDto
import com.pdmpa.stockmarketapp.domain.model.CompanyInfo
import com.pdmpa.stockmarketapp.domain.model.CompanyListing

fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity {
    return CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyInfoDto.toCompanyInfo(): CompanyInfo {
    return CompanyInfo(
        symbol = symbol.orEmpty(),
        description = description.orEmpty(),
        name = name.orEmpty(),
        country = country.orEmpty(),
        industry = industry.orEmpty()
    )
}