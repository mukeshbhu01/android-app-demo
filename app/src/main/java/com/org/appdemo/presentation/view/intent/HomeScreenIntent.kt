package com.org.appdemo.presentation.view.intent

import com.org.appdemo.presentation.view.model.ImageUiModel

sealed interface HomeScreenIntent {
    data class LoadImages(val query: String) : HomeScreenIntent
    data class OnImageSelect(val selectedImage: ImageUiModel) : HomeScreenIntent
}