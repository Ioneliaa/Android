package com.pdmpa.stockmarketapp.di

import android.app.Application
import androidx.work.WorkManager
import com.pdmpa.stockmarketapp.domain.notifications.WorkerProviderRepository
import com.pdmpa.stockmarketapp.domain.notifications.WorkerProviderRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object WorkManagerModule {

    @Provides
    @Singleton
    fun providesWorkManger(application: Application) =
        WorkManager.getInstance(application)

    @Provides
    @Singleton
    fun providesWorkerProviderRepository(workManager: WorkManager): WorkerProviderRepository {
        return WorkerProviderRepositoryImpl(workManager)
    }
}