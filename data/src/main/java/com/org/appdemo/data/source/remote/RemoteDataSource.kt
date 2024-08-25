package com.org.appdemo.data.source.remote

import com.org.appdemo.domain.model.ImageModel
import com.org.appdemo.domain.Result

interface RemoteDataSource {
    suspend fun fetchImages(query: String): Result<List<ImageModel>>
}