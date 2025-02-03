package com.emperor.moh.data.mappers

import com.emperor.moh.data.model.QuoteDto
import com.emperor.moh.domain.model.Quote

fun QuoteDto.toDomain(workType: String): Quote {
    return Quote(author, id, quote, workType)
}