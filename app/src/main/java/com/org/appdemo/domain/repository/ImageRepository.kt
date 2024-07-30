package com.org.appdemo.domain.repository

import com.org.appdemo.domain.model.ImageResponseModel
import com.org.appdemo.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface ImageRepository {
    suspend fun getImages(query : String): Flow<Result<List<ImageResponseModel>>>
}