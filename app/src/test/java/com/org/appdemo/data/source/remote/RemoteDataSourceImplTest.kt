package com.org.appdemo.data.source.remote

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.org.appdemo.DataMocks
import com.org.appdemo.MainDispatcherRule
import com.org.appdemo.common.LogUtil
import com.org.appdemo.common.NetworkUtility
import com.org.appdemo.common.exception.NoInternetException
import com.org.appdemo.data.model.NetworkImageResponse
import com.org.appdemo.data.service.ApiConstants
import com.org.appdemo.data.service.api.ApiService
import com.org.appdemo.domain.model.ImageResponseModel
import com.org.appdemo.domain.model.Result
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException


class RemoteDataSourceImplTest {
    @get:Rule
    val coroutineRule = MainDispatcherRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private val apiService: ApiService = mockk()

    @RelaxedMockK
    private val context: Context = mockk()

    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setUp() {
        mockkObject(LogUtil, NetworkUtility)
        every { LogUtil.debugLog(any(), any()) } returns Unit
        every { NetworkUtility.isInternet(any()) } returns true
        remoteDataSource = RemoteDataSourceImpl(apiService = apiService, Dispatchers.Unconfined)
    }

    @Test
    fun `test data source with no internet throw NoInternetException`() = runTest {
        coEvery { apiService.fetchImageData(any()) } throws NoInternetException("No internet")

        remoteDataSource.fetchImages("").test {
            val emittedItem = awaitItem()
            Assert.assertTrue((emittedItem as Result.Error).throwable is NoInternetException)
            Assert.assertTrue(emittedItem.code == ApiConstants.ErrorCode.NO_INTERNET)
            awaitComplete()
        }
    }

    @Test
    fun `test data error with IOException`() = runTest {
        coEvery { apiService.fetchImageData(any()) } throws IOException("IOException")

        remoteDataSource.fetchImages("").test {
            val emittedItem = awaitItem()
            Assert.assertTrue((emittedItem as Result.Error).throwable is IOException)
            Assert.assertTrue(emittedItem.message.contains("IOException", ignoreCase = true))
            awaitComplete()
        }
    }


    @Test
    fun `test data source success with empty list`() = runTest {
        val expected = NetworkImageResponse(emptyList())
        coEvery { apiService.fetchImageData(any()) } returns expected

        remoteDataSource.fetchImages("").test {
            val emittedItem = awaitItem()
            Assert.assertTrue(emittedItem is Result.Success)
            Assert.assertEquals(
                Result.Success(listOf<ImageResponseModel>()),
                emittedItem
            )
            awaitComplete()
        }
    }

    @Test
    fun `test data error with Exception`() = runTest {
        val exception = Exception("Exception")
        coEvery { apiService.fetchImageData(any()) } throws exception

        remoteDataSource.fetchImages("").test {
            val emittedItem = awaitItem()
            Assert.assertEquals(true, emittedItem is Result.Error)
            Assert.assertEquals(true, (emittedItem as Result.Error).throwable is Exception)
            awaitComplete()
        }
    }

    @Test
    fun `test data source success`() = runTest {
        val expected = DataMocks.networkResponseModel
        coEvery { apiService.fetchImageData(any()) } returns expected

        remoteDataSource.fetchImages("").test {
            val emittedItem = awaitItem()
            Assert.assertEquals(true, emittedItem is Result.Success)
            Assert.assertEquals(
                (emittedItem as Result.Success).responseData,
                DataMocks.imageResponseModelList
            )
            awaitComplete()
        }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}