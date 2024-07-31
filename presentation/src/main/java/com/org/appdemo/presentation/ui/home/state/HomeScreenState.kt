package com.org.appdemo.presentation.ui.home.state

import com.org.appdemo.domain.model.ImageModel

sealed class HomeScreenState {
    data object InitialState: HomeScreenState()
    data object Loading : HomeScreenState()
    data class Success(val images: List<ImageModel>) : HomeScreenState()
    data class Error(val message: String, val code: String = "") : HomeScreenState()
}