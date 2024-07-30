package com.org.appdemo.data.service

import com.org.appdemo.data.service.api.ApiService
import retrofit2.Retrofit

class ApiServiceManager(private val retrofit: Retrofit) {

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}