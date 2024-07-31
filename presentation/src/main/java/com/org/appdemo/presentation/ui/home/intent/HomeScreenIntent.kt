package com.org.appdemo.presentation.ui.home.intent

import com.org.appdemo.domain.model.ImageModel

sealed class HomeScreenIntent {
    data class LoadImages(val query: String) : HomeScreenIntent()
    data class OnImageSelect(val selectedImage: ImageModel) : HomeScreenIntent()
}