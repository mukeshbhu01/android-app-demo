package com.org.appdemo.domain.model

data class ImageModel(
    val id: String,
    val url: String,
    val prompt: String,
    val model: String,
    val promptId: String,
    val width: Int,
    val height: Int
)

