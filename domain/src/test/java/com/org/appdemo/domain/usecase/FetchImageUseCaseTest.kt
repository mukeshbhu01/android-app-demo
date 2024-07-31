package com.org.appdemo.domain.usecase

import android.util.Log
import com.org.appdemo.domain.repository.HomeImageRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test


class FetchImageUseCaseTest {

    private lateinit var useCase: FetchImageUseCase
    private lateinit var homeImageRepository: HomeImageRepository

    @Before
    fun setup() {
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
        homeImageRepository = mockk()
        useCase = FetchImageUseCase(homeImageRepository)
    }

    @Test
    fun `verify remoteDataSource fetchImages`() = runTest {
        coEvery { homeImageRepository.getImages(any()) } returns mockk()
        runBlocking {
            useCase("")
        }
        coVerify(exactly = 1) { homeImageRepository.getImages(any()) }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

}