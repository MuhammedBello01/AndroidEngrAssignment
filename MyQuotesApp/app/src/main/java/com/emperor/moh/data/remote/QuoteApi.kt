package com.emperor.moh.data.remote

import com.emperor.moh.data.model.QuoteDto
import retrofit2.http.GET

interface QuoteApi {

    // https://dumnyjson.com/quotes/random

    @GET("quotes/random")
    suspend fun getQuote(): QuoteDto
}