package com.org.appdemo.domain.mapper

import com.org.appdemo.DataMocks
import com.org.appdemo.domain.model.ImageResponseModel
import com.org.appdemo.presentation.view.model.ImageUiModel
import org.junit.Assert
import org.junit.Test


class UiModelMapperKtTest {

    @Test
    fun `test mapping ImageResponseModel into ImageUiModel` () {
        val responseModels: List<ImageResponseModel> = DataMocks.imageResponseModelList
        val uiModels: List<ImageUiModel> = DataMocks.imageUiModelList

        val result = responseModels.toImageUiModel()
        Assert.assertEquals(uiModels, result)
    }

    @Test
    fun `test mapping from ImageResponseModel to ImageUiModel with empty list`() {
        val responseModels = emptyList<ImageResponseModel>()
        val uiModels = emptyList<ImageUiModel>()

        val result = responseModels.toImageUiModel()

        Assert.assertEquals(uiModels, result)
    }

    @Test
    fun `test mapping from ImageResponseModel to ImageUiModel with single item`() {
        val responseModels : List<ImageResponseModel> = DataMocks.singleImageResponses
        val uiModels : List<ImageUiModel> = DataMocks.singleImageUiModels

        val result = responseModels.toImageUiModel()

        Assert.assertEquals(uiModels, result)
    }

}