package com.org.appdemo.presentation.view.intent

import com.org.appdemo.domain.model.ImageResponseModel

sealed interface HomeScreenIntent {
    data class LoadImages(val query: String) : HomeScreenIntent
    data class SearchQuery(val searchQuery: String) : HomeScreenIntent
    data class OnImageSelect(val selectedImage: ImageResponseModel) : HomeScreenIntent
}