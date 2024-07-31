package com.org.appdemo.data.di

import com.org.appdemo.data.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {
    @Singleton
    @Provides
    fun provideApiServiceInstance(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}