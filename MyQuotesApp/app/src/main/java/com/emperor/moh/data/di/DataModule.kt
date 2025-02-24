package com.emperor.moh.data.di

import android.content.Context
import android.os.Build
import androidx.work.WorkManager
import com.emperor.moh.data.local.QuoteDao
import com.emperor.moh.data.local.QuoteDatabase
import com.emperor.moh.data.remote.QuoteApi
import com.emperor.moh.data.repository.QuoteRepositoryImpl
import com.emperor.moh.domain.repository.QuoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .connectionSpecs(listOf(ConnectionSpec.MODERN_TLS)) // Enforces TLS 1.2+
        .build()

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideQuoteApi(retrofit: Retrofit): QuoteApi{
        return retrofit.create(QuoteApi::class.java)
    }

    @Singleton
    @Provides
    fun provideQuoteDatabase(@ApplicationContext context: Context): QuoteDatabase {
        return QuoteDatabase.getInstance(context)
    }

    @Provides
    fun provideQuoteDao(quoteDatabase: QuoteDatabase): QuoteDao {
        return quoteDatabase.getQuoteDao()
    }

    @Provides
    @Singleton
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager {
        return WorkManager.getInstance(context)
    }

    @Provides
    fun provideQuoteRepository(workManager: WorkManager, quoteDao: QuoteDao): QuoteRepository {
        return QuoteRepositoryImpl(workManager, quoteDao)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(okHttpClient: OkHttpClient.Builder): OkHttpClient.Builder {
        return okHttpClient
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .apply {
                addInterceptor{ chain ->
                    val request = chain.request()
                    val url = request.url()
                    if (!url.isHttps){
                        throw IllegalStateException("Only Https connections are allowed")
                    }
                    chain.proceed(request)
                }
            }
    }

}