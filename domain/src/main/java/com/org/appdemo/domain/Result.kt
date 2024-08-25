package com.org.appdemo.domain


sealed class Result<out T> {
    data class Success<T>(val responseData: T) : Result<T>()
    data class Error(val code: String = "", val message: String = "", val throwable: Throwable? = null) :
        Result<Nothing>()
}