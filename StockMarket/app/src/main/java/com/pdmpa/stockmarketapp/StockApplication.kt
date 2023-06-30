package com.pdmpa.stockmarketapp

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.pdmpa.stockmarketapp.domain.notifications.CreateWorkUseCase
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class StockApplication : Application(), Configuration.Provider {
    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var createWorkUseCase: CreateWorkUseCase


    override fun onCreate() {
        super.onCreate()
        createWorkUseCase.invoke()
    }

    override fun getWorkManagerConfiguration() = Configuration.Builder()
        .setWorkerFactory(workerFactory)
        .build()
}