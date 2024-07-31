package com.org.appdemo.data.service

import com.org.appdemo.data.dto.ImageResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search")
    suspend fun fetchImageData(@Query("q") query: String): Response<ImageResponseDto>?
}