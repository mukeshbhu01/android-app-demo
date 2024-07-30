package com.org.appdemo.data.model

import com.google.gson.annotations.SerializedName

data class NetworkImageModel(
    val id: String? = null,
    val gallery: String? = null,
    val src: String? = null,
    val srcSmall: String? = null,
    val prompt: String? = null,
    val width: Int = 0,
    val height: Int = 0,
    val seed: String? = null,
    val grid: String? = null,
    val model: String? = null,
    val guidance: Int = 0,
    @SerializedName("promptid") val promptId: String? = null
)
