package com.emperor.moh.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emperor.moh.domain.model.Quote
import com.emperor.moh.domain.usecase.GetAllQuotesFromDbUseCase
import com.emperor.moh.domain.usecase.GetQuotesUseCase
import com.emperor.moh.domain.usecase.SetupPeriodicWorkRequestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllQuotesFromDbUseCase: GetAllQuotesFromDbUseCase,
    private val getQuoteUseCase: GetQuotesUseCase,
    private val setupPeriodicWorkRequestUseCase: SetupPeriodicWorkRequestUseCase
) : ViewModel(){

    val uiState = getAllQuotesFromDbUseCase.invoke()
        .map { UiState(it) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, UiState(emptyList()))

    init {
        setupPeriodicWorkRequestUseCase.invoke()
    }

    fun getQuote() = getQuoteUseCase.invoke()
}

data class UiState(val data: List<Quote>)