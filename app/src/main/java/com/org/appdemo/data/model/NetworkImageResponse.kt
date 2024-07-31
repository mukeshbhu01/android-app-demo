package com.org.appdemo.data.model

import com.google.gson.annotations.SerializedName

data class NetworkImageResponse(
    @SerializedName("images") val images: List<NetworkImageModel> = emptyList()
)
