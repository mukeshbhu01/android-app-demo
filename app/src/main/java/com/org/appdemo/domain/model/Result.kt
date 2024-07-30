package com.org.appdemo.domain.model


sealed interface Result<out T> {
    data class Success<out T>(val responseData: T) : Result<T>
    data class Error(val message: String, val code: String = "", val throwable: Throwable? = null) :
        Result<Nothing>
}