package com.org.appdemo

import com.org.appdemo.data.model.ImageModelDto
import com.org.appdemo.data.model.ImageResponse
import com.org.appdemo.domain.model.ImageResponseModel
import com.org.appdemo.presentation.view.model.ImageUiModel

object DataMocks {
    val singleDtoModel: List<ImageModelDto> = listOf(
        ImageModelDto(
            id = "Cat1",
            src = "url",
            width = 720,
            height = 1080
        )
    )

    val singleImageResponses: List<ImageResponseModel> = listOf(
        ImageResponseModel(
            id = "Cat1",
            url = "url",
            width = 720,
            height = 1080
        )
    )
    val singleImageUiModels: List<ImageUiModel> = listOf(
        ImageUiModel(
            id = "Cat1",
            url = "url",
            width = 720,
            height = 1080
        )
    )

    val imageUiModelList = listOf(
        ImageUiModel(
            id = "Cat1",
            url = "url",
            width = 720,
            height = 1080
        ),
        ImageUiModel(
            id = "Cat2",
            url = "url",
            width = 720,
            height = 1080
        )
    )

    val imageResponseModelList = listOf(
        ImageResponseModel(
            id = "Cat1",
            url = "url",
            width = 720,
            height = 1080
        ),
        ImageResponseModel(
            id = "Cat2",
            url = "url",
            width = 720,
            height = 1080
        )
    )

    val imagesModelDtoList = listOf(
        ImageModelDto(
            id = "Cat1",
            src = "url",
            width = 720,
            height = 1080
        ),
        ImageModelDto(
            id = "Cat2",
            src = "url",
            width = 720,
            height = 1080
        )
    )

    val apiResponseModel = ImageResponse(imagesModelDtoList)
}