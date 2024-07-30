package com.org.appdemo.data.di

import com.org.appdemo.domain.repository.ImageRepository
import com.org.appdemo.data.repository.ImageRepositoryImpl
import com.org.appdemo.data.source.remote.RemoteDataSource
import com.org.appdemo.data.source.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Singleton
    @Binds
    fun provideImageRepository(imageRepositoryImpl: ImageRepositoryImpl): ImageRepository

    @Binds
    @Singleton
    fun provideRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource


}