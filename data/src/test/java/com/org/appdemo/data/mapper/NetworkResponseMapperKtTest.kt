package com.org.appdemo.data.mapper

import com.org.appdemo.data.DataMocks
import com.org.appdemo.data.dto.ImageModelDto
import com.org.appdemo.domain.model.ImageModel
import org.junit.Assert
import org.junit.Test


class NetworkResponseMapperKtTest {

    @Test
    fun `test mapping ImageModelDto into ImageResponseModel` () {
        val dtoModels: List<ImageModelDto> = DataMocks.networkImagesModelList
        val responseModels: List<ImageModel> = DataMocks.imageModelLists

        val result = dtoModels.toImageResponseDomainModel()
        Assert.assertEquals(responseModels, result)
    }

    @Test
    fun `test mapping from ImageModelDto to ImageResponseModel with empty list`() {
        val dtoModels: List<ImageModelDto> = emptyList()
        val responseModels: List<ImageModel> = emptyList()

        val result = dtoModels.toImageResponseDomainModel()
        Assert.assertEquals(responseModels, result)
    }

    @Test
    fun `test mapping from ImageModelDto to ImageResponseModel with single item`() {
        val dtoModels: List<ImageModelDto> = DataMocks.singleNetworkImages
        val responseModels: List<ImageModel> = DataMocks.singleImageResponses

        val result = dtoModels.toImageResponseDomainModel()
        Assert.assertEquals(responseModels, result)
    }
}