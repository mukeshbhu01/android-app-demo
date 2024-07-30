package com.org.appdemo.data.model

import com.google.gson.annotations.SerializedName

data class ImageModelDto(
    @SerializedName("id") val id: String? = null,
    @SerializedName("gallery") val gallery: String? = null,
    @SerializedName("src") val src: String? = null,
    @SerializedName("srcSmall") val srcSmall: String? = null,
    @SerializedName("prompt") val prompt: String? = null,
    @SerializedName("width") val width: Int = 0,
    @SerializedName("height") val height: Int = 0,
    @SerializedName("seed") val seed: String? = null,
    @SerializedName("grid") val grid: String? = null,
    @SerializedName("model") val model: String? = null,
    @SerializedName("guidance") val guidance: Int = 0,
    @SerializedName("promptid") val promptId: String? = null
)
