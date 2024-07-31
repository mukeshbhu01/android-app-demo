package com.org.appdemo.domain.usecase

import com.org.appdemo.common.LogUtil
import com.org.appdemo.domain.repository.ImageRepository
import com.org.appdemo.domain.model.ImageResponseModel
import com.org.appdemo.domain.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class FetchImageUseCaseImpl @Inject constructor(
    private val imageRepository: ImageRepository
) : FetchImageUseCase {
    override suspend operator fun invoke(searchQuery: String): Flow<Result<List<ImageResponseModel>>> {
        LogUtil.debugLog(log = "FetchImageUseCase use case performed ")
        return imageRepository.getImages(searchQuery)
    }
}