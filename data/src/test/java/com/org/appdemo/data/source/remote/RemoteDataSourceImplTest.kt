package com.org.appdemo.data.source.remote

import android.util.Log
import com.org.appdemo.network.exception.NoInternetException
import com.org.appdemo.network.utility.NetworkUtility
import com.org.appdemo.data.DataMocks
import com.org.appdemo.data.MainDispatcherRule
import com.org.appdemo.domain.model.ImageModel
import com.org.appdemo.domain.model.Result
import com.org.appdemo.data.service.ApiService
import com.org.appdemo.data.dto.ImageResponseDto
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.mockkStatic
import io.mockk.spyk
import io.mockk.unmockkAll
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


class RemoteDataSourceImplTest {
    @get:Rule
    val coroutineRule = MainDispatcherRule()


    private val apiService: ApiService = mockk()
    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setUp() {
        mockkObject(NetworkUtility)
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
        every { NetworkUtility.isInternet(any()) } returns true
        remoteDataSource = RemoteDataSourceImpl(apiService = apiService)
    }

    @Test
    fun `test fetchImages data source with no internet throw NoInternetException`() = runTest {
        coEvery { apiService.fetchImageData(any()) } throws NoInternetException("No internet")

        runBlocking {
            val emittedItem = remoteDataSource.fetchImages("")
            Assert.assertTrue((emittedItem as Result.Error).throwable is NoInternetException)
        }
    }

    @Test
    fun `test fetchImages data error with IOException`() = runTest {
        coEvery { apiService.fetchImageData(any()) } throws IOException("IOException")

        runBlocking {
            val emittedItem = remoteDataSource.fetchImages("")
            Assert.assertTrue((emittedItem as Result.Error).throwable is IOException)
        }
    }

    @Test
    fun `test fetchImages data error with HttpException`() = runTest {
        val exception = mockk<HttpException>()
        every { exception.printStackTrace() } returns Unit
        every { exception.code() } returns 101
        every { exception.message } returns "httpException"
        coEvery { apiService.fetchImageData(any()) } throws exception

        runBlocking {
            val emittedItem = remoteDataSource.fetchImages("")
            Assert.assertTrue((emittedItem as Result.Error).throwable is HttpException)
            Assert.assertTrue(emittedItem.code == "101")
            Assert.assertTrue(emittedItem.message == "httpException")
        }
    }


    @Test
    fun `test fetchImages data source success with empty list`() = runTest {
        val mock = Response.success(ImageResponseDto())
        coEvery { apiService.fetchImageData(any()) } returns mock

        runBlocking {
            val emittedItem = remoteDataSource.fetchImages("")
            Assert.assertTrue(emittedItem is Result.Success)
            Assert.assertEquals(
                Result.Success(listOf<ImageModel>()),
                emittedItem
            )
        }
    }

    @Test
    fun `test fetchImages data error with Exception`() = runTest {
        val exception = Exception("Exception")
        coEvery { apiService.fetchImageData(any()) } throws exception

        runBlocking {
            val emittedItem = remoteDataSource.fetchImages("")
            Assert.assertEquals(true, emittedItem is Result.Error)
            Assert.assertEquals(true, (emittedItem as Result.Error).throwable is Exception)
        }
    }

    @Test
    fun `test fetchImages data source success`() = runTest {
        val mock = Response.success(DataMocks.networkResponseModel)
        coEvery { apiService.fetchImageData(any()) } returns mock

        runBlocking {
            val emittedItem = remoteDataSource.fetchImages("")
            Assert.assertEquals(true, emittedItem is Result.Success)
            Assert.assertEquals(
                (emittedItem as Result.Success).responseData,
                DataMocks.imageModelLists
            )
        }
    }

    @Test
    fun `test fetchImages data source not success`() = runTest {
        val mockResponse = Response.success(DataMocks.networkResponseModel)
        val spyObject = spyk(mockResponse)
        every { spyObject.isSuccessful } returns false
        coEvery { apiService.fetchImageData(any()) } returns spyObject

        runBlocking {
            val emittedItem = remoteDataSource.fetchImages("")
            Assert.assertEquals(true, emittedItem is Result.Error)
        }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}
