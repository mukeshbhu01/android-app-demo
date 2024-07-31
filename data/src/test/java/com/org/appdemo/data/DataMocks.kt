package com.org.appdemo.data

import com.org.appdemo.data.dto.ImageModelDto
import com.org.appdemo.data.dto.ImageResponseDto
import com.org.appdemo.domain.model.ImageModel

object DataMocks {
    val singleNetworkImages: List<ImageModelDto> = listOf(
        ImageModelDto(
            id = "Cat1",
            src = "url",
            width = 720,
            height = 1080
        )
    )

    val singleImageResponses: List<ImageModel> = listOf(
        ImageModel(
            id = "Cat1",
            url = "url",
            prompt = "abc",
            model = "abc",
            promptId = "abc",
            width = 720,
            height = 1080
        )
    )

    val imageModelLists = listOf(
        ImageModel(
            id = "Cat1",
            url = "url",
            prompt = "abc",
            model = "abc",
            promptId = "abc",
            width = 720,
            height = 1080
        ),
        ImageModel(
            id = "Cat2",
            url = "url",
            prompt = "abc",
            model = "abc",
            promptId = "abc",
            width = 720,
            height = 1080
        )
    )

    val networkImagesModelList = listOf(
        ImageModelDto(
            id = "Cat1",
            src = "url",
            prompt = "abc",
            model = "abc",
            promptId = "abc",
            width = 720,
            height = 1080
        ),
        ImageModelDto(
            id = "Cat2",
            src = "url",
            prompt = "abc",
            model = "abc",
            promptId = "abc",
            width = 720,
            height = 1080
        )
    )

    val networkResponseModel =
        ImageResponseDto(networkImagesModelList)
}