package com.org.appdemo.data.source.remote

import com.org.appdemo.common.LogUtil
import com.org.appdemo.common.exception.NoInternetException
import com.org.appdemo.data.mapper.toImageResponseModel
import com.org.appdemo.data.service.ApiConstants
import com.org.appdemo.data.service.api.ApiService
import com.org.appdemo.domain.model.ImageResponseModel
import com.org.appdemo.domain.model.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RemoteDataSource {

    override suspend fun fetchImages(query: String): Flow<Result<List<ImageResponseModel>>> {
        LogUtil.debugLog(log = "Executing image query: $query")
        return flow {
            runCatching {
                val imageResponse = apiService.fetchImageData(query)
                LogUtil.debugLog(log = imageResponse.let { if (it.images.isEmpty()) "No image available" else "Images ${it.images}" })
                emit(Result.Success(responseData = imageResponse.images.toImageResponseModel()))
            }.onFailure { cause ->
                cause.printStackTrace()
                when (cause) {
                    is NoInternetException -> emit(
                        Result.Error(
                            message = "No Internet connection!!",
                            code = ApiConstants.ErrorCode.NO_INTERNET,
                            throwable = cause
                        )
                    )

                    is IOException -> emit(
                        Result.Error(
                            message = cause.message ?: "IOException",
                            throwable = cause
                        )
                    )

                    is HttpException -> emit(
                        Result.Error(
                            message = cause.message ?: "HttpException",
                            code = cause.code().toString(),
                            throwable = cause
                        )
                    )

                    else -> emit(
                        Result.Error(
                            message = cause.message ?: "Exception",
                            throwable = cause
                        )
                    )
                }
            }
        }.flowOn(ioDispatcher)
    }
}