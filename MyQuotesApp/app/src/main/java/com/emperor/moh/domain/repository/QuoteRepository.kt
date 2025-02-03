package com.emperor.moh.domain.repository

import com.emperor.moh.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {

    fun getQuote()

    fun getAllQuotes(): Flow<List<Quote>>

    fun setupPeriodicWorkRequest()
}