package com.emperor.moh.domain.usecase

import com.emperor.moh.domain.repository.QuoteRepository
import javax.inject.Inject

class GetAllQuotesFromDbUseCase @Inject constructor(private val quoteRepository: QuoteRepository) {

    operator fun invoke() = quoteRepository.getAllQuotes()
}