package com.org.appdemo.domain.usecase

import com.org.appdemo.domain.model.ImageResponseModel
import com.org.appdemo.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface FetchImageUseCase {
    suspend operator fun invoke(searchQuery: String): Flow<Result<List<ImageResponseModel>>>
}