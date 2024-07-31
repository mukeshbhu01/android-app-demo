package com.org.appdemo.data.mapper

import com.org.appdemo.data.dto.ImageModelDto
import com.org.appdemo.domain.model.ImageModel


fun List<ImageModelDto>.toImageResponseDomainModel() = this.map { item ->
    ImageModel(
        id = item.id ?: "",
        url = item.src ?: "",
        prompt = item.prompt ?: "",
        model = item.model ?: "",
        promptId = item.promptId ?: "",
        width = item.width,
        height = item.height
    )
}
