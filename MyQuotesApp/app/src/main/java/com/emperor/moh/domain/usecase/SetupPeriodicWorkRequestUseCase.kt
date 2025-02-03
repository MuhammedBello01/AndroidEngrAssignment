package com.emperor.moh.domain.usecase

import com.emperor.moh.domain.repository.QuoteRepository
import javax.inject.Inject

class SetupPeriodicWorkRequestUseCase @Inject constructor(private val quoteRepository: QuoteRepository)  {

    operator fun invoke() = quoteRepository.setupPeriodicWorkRequest()
}