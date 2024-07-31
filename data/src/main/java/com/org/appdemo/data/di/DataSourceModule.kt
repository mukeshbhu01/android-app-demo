package com.org.appdemo.data.di

import com.org.appdemo.data.repository.HomeImageRepositoryImpl
import com.org.appdemo.data.source.remote.RemoteDataSource
import com.org.appdemo.data.source.remote.RemoteDataSourceImpl
import com.org.appdemo.domain.repository.HomeImageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface DataSourceModule {

    @ViewModelScoped
    @Binds
    fun provideRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

    @ViewModelScoped
    @Binds
    fun provideImageRepository(imageRepositoryImpl: HomeImageRepositoryImpl): HomeImageRepository
}