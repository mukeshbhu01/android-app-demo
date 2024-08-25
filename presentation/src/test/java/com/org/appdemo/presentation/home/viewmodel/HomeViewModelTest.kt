package com.org.appdemo.presentation.home.viewmodel

import android.util.Log
import app.cash.turbine.test
import com.org.appdemo.domain.Result
import com.org.appdemo.domain.usecase.FetchImageUseCase
import com.org.appdemo.presentation.DataMocks
import com.org.appdemo.presentation.MainDispatcherRule
import com.org.appdemo.presentation.ui.home.effect.HomeScreenEffect
import com.org.appdemo.presentation.ui.home.intent.HomeScreenIntent
import com.org.appdemo.presentation.ui.home.state.HomeScreenState
import com.org.appdemo.presentation.ui.home.viewmodel.HomeViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class HomeViewModelTest {
    @get:Rule
    val coroutineRule = MainDispatcherRule()

    private var useCase: FetchImageUseCase = mockk()
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
        viewModel = HomeViewModel(useCase)
    }

    @Test
    fun `test Loading state`() = runTest {
        coEvery { useCase(any()) } returns Result.Success(emptyList())
        viewModel.sendActionEvent(HomeScreenIntent.LoadImages("cat"))
        runBlocking {
            Assert.assertTrue(viewModel.homeScreenState.value is HomeScreenState.Loading)
        }
    }


    @Test
    fun `test success state with empty images list`() = runTest {
        coEvery { useCase(any()) } returns Result.Success(emptyList())

        viewModel.sendActionEvent(HomeScreenIntent.LoadImages("cat"))
        viewModel.homeScreenState.test {
            val emittedItem = awaitItem()
            Assert.assertTrue(emittedItem is HomeScreenState.Success)
            Assert.assertEquals(HomeScreenState.Success(emptyList()), emittedItem)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `test success state with ImageResponseModel`() = runTest {
        val images = DataMocks.imageModelLists
        coEvery { useCase(any()) } returns Result.Success(responseData = images)
        viewModel.sendActionEvent(HomeScreenIntent.LoadImages("cat"))

        viewModel.homeScreenState.test {
            val emittedItem = awaitItem()
            Assert.assertTrue(emittedItem is HomeScreenState.Success)
            Assert.assertEquals(
                HomeScreenState.Success(DataMocks.imageModelLists),
                emittedItem
            )
            cancelAndIgnoreRemainingEvents()
        }
    }


    @Test
    fun `test success state with images`() = runTest {
        val images = DataMocks.imageModelLists

        coEvery { useCase(any()) } returns Result.Success(responseData = images)
        viewModel.sendActionEvent(HomeScreenIntent.LoadImages("cat"))
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
        coEvery { useCase(any()) } returns Result.Error()
        viewModel.sendActionEvent(HomeScreenIntent.LoadImages("cat"))

        viewModel.homeScreenState.test {
            val emittedItem = awaitItem()
            Assert.assertTrue(emittedItem is HomeScreenState.Error)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `test OnImageSelected state`() = runTest {
        viewModel = HomeViewModel(useCase)
        viewModel.effect.test {
            viewModel.sendActionEvent(HomeScreenIntent.OnImageSelect(DataMocks.imageModelLists[0]))
            val emittedItem = awaitItem()
            Assert.assertTrue(emittedItem is HomeScreenEffect.NavigateToDetail)
            Assert.assertTrue(emittedItem == HomeScreenEffect.NavigateToDetail(DataMocks.imageModelLists[0]))
            cancelAndIgnoreRemainingEvents()
        }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}
