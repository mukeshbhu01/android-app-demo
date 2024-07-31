package com.org.appdemo.domain.repository

import com.org.appdemo.domain.model.ImageModel
import com.org.appdemo.domain.model.Result

interface HomeImageRepository {
    suspend fun getImages(query : String): Result<List<ImageModel>>
}