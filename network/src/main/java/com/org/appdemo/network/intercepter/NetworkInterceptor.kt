package com.org.appdemo.network.intercepter

import android.content.Context
import com.org.appdemo.network.exception.NoInternetException
import com.org.appdemo.network.utility.NetworkUtility
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NetworkInterceptor @Inject constructor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (NetworkUtility.isInternet(context).not()) //Interceptor for no internet connection
            throw NoInternetException("No Internet")
        return chain.proceed(chain.request())
    }
}