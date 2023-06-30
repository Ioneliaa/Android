package com.pdmpa.stockmarketapp.domain.notifications

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.pdmpa.stockmarketapp.domain.repository.StockRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class DashCoinWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val stocksRepository: StockRepository,
): CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {

        return try {

            stocksRepository.getIntradayInfo("AAPL").let {
                it.data?.map { coin ->
                    if (coin.close >= 0 ) {
                        NotificationUtils.showNotification(
                            context = applicationContext,
                            title = "Market Status",
                            description = "looking positive today \uD83D\uDE0D "
                        )
                    } else {
                        NotificationUtils.showNotification(
                            context = applicationContext,
                            title ="Market Status",
                            description = "on the negative side today \uD83D\uDE21"
                        )
                    }
                }
            }

            Result.success()
        }catch (exception: Exception) {
            Result.failure()
        }
    }
}