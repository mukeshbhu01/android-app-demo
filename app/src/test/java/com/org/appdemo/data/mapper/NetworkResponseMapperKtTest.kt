package com.org.appdemo.data.mapper

import com.org.appdemo.DataMocks
import com.org.appdemo.data.model.NetworkImageModel
import com.org.appdemo.domain.model.ImageResponseModel
import org.junit.Assert
import org.junit.Test


class NetworkResponseMapperKtTest {

    @Test
    fun `test mapping ImageModelDto into ImageResponseModel` () {
        val dtoModels: List<NetworkImageModel> = DataMocks.networkImagesModelList
        val responseModels: List<ImageResponseModel> = DataMocks.imageResponseModelList

        val result = dtoModels.toImageResponseModel()
        Assert.assertEquals(responseModels, result)
    }

    @Test
    fun `test mapping from ImageModelDto to ImageResponseModel with empty list`() {
        val dtoModels: List<NetworkImageModel> = emptyList()
        val responseModels: List<ImageResponseModel> = emptyList()

        val result = dtoModels.toImageResponseModel()
        Assert.assertEquals(responseModels, result)
    }

    @Test
    fun `test mapping from ImageModelDto to ImageResponseModel with single item`() {
        val dtoModels: List<NetworkImageModel> = DataMocks.singleNetworkImages
        val responseModels: List<ImageResponseModel> = DataMocks.singleImageResponses

        val result = dtoModels.toImageResponseModel()
        Assert.assertEquals(responseModels, result)
    }
}