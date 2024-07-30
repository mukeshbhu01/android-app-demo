package com.org.appdemo.data.service.intercepter

import android.content.Context
import com.org.appdemo.R
import com.org.appdemo.common.NetworkUtility
import com.org.appdemo.common.exception.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response
import okio.IOException
import javax.inject.Inject

class NetworkInterceptor @Inject constructor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (NetworkUtility.isInternet(context).not()) //Interceptor for no internet connection
            throw NoInternetException(context.getString(R.string.no_internet_connection))
        return chain.proceed(chain.request())
    }
}