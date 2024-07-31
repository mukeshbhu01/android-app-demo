package com.org.appdemo.presentation.ui.home.effect

import com.org.appdemo.domain.model.ImageModel

sealed class HomeScreenEffect {
    data class NavigateToDetail(val selectedImage: ImageModel) : HomeScreenEffect()
}