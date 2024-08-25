package com.org.appdemo.data.source.remote

import com.org.appdemo.data.ErrorCode
import com.org.appdemo.data.NO_INTERNET_MSG
import com.org.appdemo.data.UNKNOWN_ERROR
import com.org.appdemo.data.dto.ImageResponseDto
import com.org.appdemo.data.mapper.toImageResponseDomainModel
import com.org.appdemo.data.service.ApiService
import com.org.appdemo.domain.model.ImageModel
import com.org.appdemo.domain.Result
import com.org.appdemo.network.exception.NoInternetException
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : RemoteDataSource {

    override suspend fun fetchImages(query: String): Result<List<ImageModel>> = try {
        processFetchImageResponse(response = apiService.fetchImageData(query))
    } catch (cause: Exception) {
        cause.printStackTrace()
        handleErrorState(cause = cause)
    }
}

private fun processFetchImageResponse(response: Response<ImageResponseDto>?): Result<List<ImageModel>> {
    return if (response?.isSuccessful == true) {
        response.body()?.images?.let { images ->
            Result.Success(responseData = images.toImageResponseDomainModel())
        } ?: Result.Error()
    } else {
        Result.Error()
    }
}

private fun handleErrorState(cause: Throwable?) = when (cause) {
    is NoInternetException -> Result.Error(
        message = NO_INTERNET_MSG,
        code = ErrorCode.NO_INTERNET,
        throwable = cause
    )

    is HttpException -> Result.Error(
        message = cause.message ?: UNKNOWN_ERROR,
        code = cause.code().toString(),
        throwable = cause
    )

    else -> Result.Error(
        message = cause?.message ?: UNKNOWN_ERROR,
        throwable = cause
    )
}