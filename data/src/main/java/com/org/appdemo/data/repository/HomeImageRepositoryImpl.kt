package com.org.appdemo.data.repository

import com.org.appdemo.data.source.remote.RemoteDataSource
import com.org.appdemo.domain.model.ImageModel
import com.org.appdemo.domain.model.Result
import com.org.appdemo.domain.repository.HomeImageRepository
import javax.inject.Inject

class HomeImageRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : HomeImageRepository {
    override suspend fun getImages(query : String): Result<List<ImageModel>> {
        return remoteDataSource.fetchImages(query)
    }
}