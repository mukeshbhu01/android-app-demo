package com.org.appdemo.data.model

import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("images") val images: List<ImageModelDto> = emptyList()
)
