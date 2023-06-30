package com.pdmpa.stockmarketapp.domain

import com.pdmpa.stockmarketapp.domain.model.NewsDetail
import com.pdmpa.stockmarketapp.domain.model.toNewsDetail
import com.pdmpa.stockmarketapp.domain.repository.NewsRepository
import com.pdmpa.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    operator fun invoke(filter: String): Flow<Resource<List<NewsDetail>>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getNews(filter).news.map { it.toNewsDetail() }
            emit(Resource.Success(coin))
        }catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage?: "An unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }
    }
}