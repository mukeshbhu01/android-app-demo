package com.org.appdemo.presentation

import com.org.appdemo.domain.model.ImageModel

object DataMocks {

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

}