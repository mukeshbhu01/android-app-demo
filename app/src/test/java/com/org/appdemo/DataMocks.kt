package com.org.appdemo

import com.org.appdemo.data.model.NetworkImageModel
import com.org.appdemo.data.model.NetworkImageResponse
import com.org.appdemo.domain.model.ImageResponseModel

object DataMocks {
    val singleNetworkImages: List<NetworkImageModel> = listOf(
        NetworkImageModel(
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

    val networkImagesModelList = listOf(
        NetworkImageModel(
            id = "Cat1",
            src = "url",
            width = 720,
            height = 1080
        ),
        NetworkImageModel(
            id = "Cat2",
            src = "url",
            width = 720,
            height = 1080
        )
    )

    val networkResponseModel = NetworkImageResponse(networkImagesModelList)
}