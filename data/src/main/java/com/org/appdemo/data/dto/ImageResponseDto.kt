package com.org.appdemo.data.dto

import com.google.gson.annotations.SerializedName

data class ImageResponseDto(
    @SerializedName("images") val images: List<ImageModelDto> = emptyList()
)
