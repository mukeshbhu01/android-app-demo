package com.org.appdemo.data.mapper

import com.org.appdemo.data.model.ImageModelDto
import com.org.appdemo.domain.model.ImageResponseModel


fun List<ImageModelDto>.toImageResponseModel(): List<ImageResponseModel> {
    return this.map { item ->
        ImageResponseModel(
            id = item.id ?: "",
            url = item.src ?: "",
            width = item.width,
            height = item.height
        )
    }
}