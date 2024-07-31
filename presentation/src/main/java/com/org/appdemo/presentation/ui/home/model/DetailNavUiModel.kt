package com.org.appdemo.presentation.ui.home.model

import com.google.gson.Gson
import com.org.appdemo.domain.model.ImageModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

data class DetailNavUiModel(
    val id: String,
    val url: String,
    val prompt: String,
    val model: String,
    val promptId: String,
    val width: Int,
    val height: Int
)

fun ImageModel.toDetailNavUiModel() = DetailNavUiModel(
    id = this.id,
    url = URLEncoder.encode(this.url, StandardCharsets.UTF_8.toString()),
    prompt = this.prompt,
    model = this.model,
    promptId = this.promptId,
    width = this.width,
    height = this.height
)

fun DetailNavUiModel.toJsonString(): String {
    return Gson().toJson(this)
}

fun String.jsonToDetailsUiModel(): DetailNavUiModel {
    return Gson().fromJson(this, DetailNavUiModel::class.java)
}