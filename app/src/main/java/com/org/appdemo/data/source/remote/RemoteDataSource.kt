package com.org.appdemo.data.source.remote

import com.org.appdemo.domain.model.ImageResponseModel
import com.org.appdemo.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun fetchImages(query: String): Flow<Result<List<ImageResponseModel>>>
}