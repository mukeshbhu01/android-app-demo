package com.org.appdemo.data.repository

import com.org.appdemo.data.source.remote.RemoteDataSource
import com.org.appdemo.domain.model.ImageResponseModel
import com.org.appdemo.domain.model.Result
import com.org.appdemo.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : ImageRepository {
    override suspend fun getImages(query : String): Flow<Result<List<ImageResponseModel>>> {
        return remoteDataSource.fetchImages(query)
    }
}