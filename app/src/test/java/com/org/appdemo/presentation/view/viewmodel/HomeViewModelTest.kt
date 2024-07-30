package com.org.appdemo.presentation.view.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.org.appdemo.DataMocks
import com.org.appdemo.MainDispatcherRule
import com.org.appdemo.common.LogUtil
import com.org.appdemo.domain.model.Result
import com.org.appdemo.domain.usecase.FetchImageUseCase
import com.org.appdemo.presentation.view.intent.HomeScreenIntent
import com.org.appdemo.presentation.view.state.HomeScreenState
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class HomeViewModelTest {
    @get:Rule
    val coroutineRule = MainDispatcherRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private var useCase: FetchImageUseCase = mockk()

    private lateinit var viewModel: HomeViewModel


    @Before
    fun setUp() {
        mockkObject(LogUtil)
        every { LogUtil.debugLog(any(), any()) } returns Unit
        viewModel = HomeViewModel(useCase)
    }

    @Test
    fun `test success state with empty images list`() = runTest {
        coEvery { useCase.invoke(any()) } answers { flow { emit(Result.Error(message = "")) } }
        viewModel.handleHomeIntent(HomeScreenIntent.LoadImages("cat"))

        viewModel.homeScreenState.test {
            val emittedItem = awaitItem()
            Assert.assertTrue(emittedItem is HomeScreenState.Error)
            Assert.assertEquals(HomeScreenState.Error(""), emittedItem)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `test success state with ImageResponseModel`() = runTest {
        val images = DataMocks.imageResponseModelList
        coEvery { useCase.invoke(any()) } answers { flow { emit(Result.Success(responseData = images)) } }
        viewModel.handleHomeIntent(HomeScreenIntent.LoadImages("cat"))

        viewModel.homeScreenState.test {
            val emittedItem = awaitItem()
            Assert.assertTrue(emittedItem is HomeScreenState.Success)
            Assert.assertEquals(
                HomeScreenState.Success(DataMocks.imageResponseModelList),
                emittedItem
            )
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `test success state with images`() = runTest {
        val images = DataMocks.imageResponseModelList

        coEvery { useCase.invoke(any()) } answers { flow { emit(Result.Success(responseData = images)) } }
        viewModel.handleHomeIntent(HomeScreenIntent.LoadImages("cat"))
        viewModel.homeScreenState.test {
            val emittedItem = awaitItem()
            Assert.assertTrue(emittedItem is HomeScreenState.Success)
            val emittedImageId = (emittedItem as HomeScreenState.Success).images[0].id
            Assert.assertTrue(emittedImageId.equals("Cat1", ignoreCase = true))
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `test error state`() = runTest {
        coEvery { useCase.invoke(any()) } answers { flow { emit(Result.Error(message = "")) } }
        viewModel.handleHomeIntent(HomeScreenIntent.LoadImages("cat"))

        viewModel.homeScreenState.test {
            val emittedItem = awaitItem()
            Assert.assertTrue(emittedItem is HomeScreenState.Error)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}