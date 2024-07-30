package com.org.appdemo.domain.mapper

import com.org.appdemo.domain.model.ImageResponseModel
import com.org.appdemo.presentation.view.model.ImageUiModel

fun List<ImageResponseModel>.toImageUiModel(): List<ImageUiModel> {
    return this.map { item ->
        ImageUiModel(
            id = item.id,
            url = item.url,
            width = item.width,
            height = item.height
        )
    }
}