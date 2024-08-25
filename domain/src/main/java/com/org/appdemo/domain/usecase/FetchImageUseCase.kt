package com.org.appdemo.domain.usecase

import com.org.appdemo.domain.model.ImageModel
import com.org.appdemo.domain.Result
import com.org.appdemo.domain.repository.HomeImageRepository
import javax.inject.Inject

class FetchImageUseCase @Inject constructor(
    private val homeImageRepository: HomeImageRepository
) {
    suspend operator fun invoke(searchQuery: String): Result<List<ImageModel>> =
        homeImageRepository.getImages(searchQuery)
}