package com.org.appdemo.presentation.view.state

import com.org.appdemo.domain.model.ImageResponseModel

sealed interface HomeScreenState {
    data object InitialState: HomeScreenState
    data object Loading : HomeScreenState
    data class Success(val images: List<ImageResponseModel>) : HomeScreenState
    data class Error(val message: String, val code: String = "") : HomeScreenState
}