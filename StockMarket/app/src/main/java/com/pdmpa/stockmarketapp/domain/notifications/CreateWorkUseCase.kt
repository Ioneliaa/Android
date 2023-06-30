package com.pdmpa.stockmarketapp.domain.notifications

import javax.inject.Inject

class CreateWorkUseCase @Inject constructor(
    private val workerProviderRepository: WorkerProviderRepository
) {

    operator fun invoke() = workerProviderRepository.createWork()
}