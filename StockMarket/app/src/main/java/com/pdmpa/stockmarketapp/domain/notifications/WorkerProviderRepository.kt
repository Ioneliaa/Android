package com.pdmpa.stockmarketapp.domain.notifications

import androidx.lifecycle.LiveData
import androidx.work.WorkInfo

interface WorkerProviderRepository {

    fun createWork()

    fun onWorkerSuccess(): LiveData<List<WorkInfo>>

}