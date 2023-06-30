package com.pdmpa.stockmarketapp.di

import android.app.Application
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.pdmpa.stockmarketapp.data.local.StockDatabase
import com.pdmpa.stockmarketapp.data.remote.NewsApi
import com.pdmpa.stockmarketapp.data.remote.StockApi
import com.pdmpa.stockmarketapp.data.repository.NewsRepositoryImpl
import com.pdmpa.stockmarketapp.domain.GetNewsUseCase
import com.pdmpa.stockmarketapp.domain.StocksUseCases
import com.pdmpa.stockmarketapp.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStockApi(): StockApi {
        return Retrofit.Builder()
            .baseUrl(StockApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideStockNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(StockApi.NEWS_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }


    @Provides
    @Singleton
    fun provideStockDatabase(app: Application): StockDatabase {
        return Room.databaseBuilder(
            app,
            StockDatabase::class.java,
            "stockdb.db"
        ).build()
    }

    @Provides
    fun providesFirebaseAuthentication(): FirebaseAuth = Firebase.auth

    @Provides
    @Singleton
    fun providesNewsRepository(
        api: NewsApi
    ): NewsRepository{
        return NewsRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providesStocksUseCases(
        repository: NewsRepository
    ): StocksUseCases {
        return StocksUseCases(
            getNews = GetNewsUseCase(repository),
        )
    }
}