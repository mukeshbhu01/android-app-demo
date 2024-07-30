package com.org.appdemo.data.di

import android.content.Context
import com.org.appdemo.data.service.ApiConstants
import com.org.appdemo.data.service.ApiServiceManager
import com.org.appdemo.data.service.api.ApiService
import com.org.appdemo.data.service.intercepter.NetworkInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideIODispatchers() : CoroutineDispatcher = Dispatchers.IO

    @Singleton
    @Provides
    fun provideNetworkInterceptor(@ApplicationContext context: Context): NetworkInterceptor =
        NetworkInterceptor(context)

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideOkHttpClient(
        networkInterceptor: NetworkInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(networkInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()


    @Singleton
    @Provides
    fun buildRetrofit(
        okHttpClient: OkHttpClient,
        converter: GsonConverterFactory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(converter)
            .client(okHttpClient)
            .build()


    @Singleton
    @Provides
    fun provideApiManager(retrofit: Retrofit): ApiServiceManager = ApiServiceManager(retrofit)

    @Singleton
    @Provides
    fun provideApiServiceInstance(apiServiceManager: ApiServiceManager): ApiService =
        apiServiceManager.apiService
}