package com.org.appdemo.data.service.api

import com.org.appdemo.data.model.ImageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search")
    suspend fun fetchImageData(@Query("q") query: String): ImageResponse
}