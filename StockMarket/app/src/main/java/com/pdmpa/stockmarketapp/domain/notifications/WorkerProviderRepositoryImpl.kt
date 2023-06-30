package com.pdmpa.stockmarketapp.domain.notifications

import androidx.work.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WorkerProviderRepositoryImpl @Inject constructor(
    private val workManager: WorkManager
): WorkerProviderRepository {

    private val workConstraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()


    override fun createWork() {
        val workRequest = PeriodicWorkRequestBuilder<DashCoinWorker>(
            60, TimeUnit.MINUTES,
            60, TimeUnit.MINUTES
        ).setConstraints(workConstraints)
            .addTag("syncData").build()

        workManager.enqueueUniquePeriodicWork(
            "syncDataWorkName",
            ExistingPeriodicWorkPolicy.REPLACE,
            workRequest
        )
    }

    override fun onWorkerSuccess() = workManager.getWorkInfosByTagLiveData("syncData")
}