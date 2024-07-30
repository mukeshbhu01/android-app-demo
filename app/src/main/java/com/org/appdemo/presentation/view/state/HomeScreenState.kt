package com.org.appdemo.presentation.view.state

import com.org.appdemo.presentation.view.model.ImageUiModel

sealed interface HomeScreenState {
    data object InitialState: HomeScreenState
    data object Loading : HomeScreenState
    data class Success(val images: List<ImageUiModel>) : HomeScreenState
    data class Error(val message: String, val code: String = "") : HomeScreenState
    data object ShowEmptyQueryToast : HomeScreenState
}