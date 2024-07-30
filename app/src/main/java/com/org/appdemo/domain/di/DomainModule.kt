package com.org.appdemo.domain.di

import com.org.appdemo.domain.usecase.FetchImageUseCase
import com.org.appdemo.domain.usecase.FetchImageUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DomainModule {

    @Binds
    @Singleton
    fun provideHomeUseCase(useCaseImpl: FetchImageUseCaseImpl): FetchImageUseCase
}