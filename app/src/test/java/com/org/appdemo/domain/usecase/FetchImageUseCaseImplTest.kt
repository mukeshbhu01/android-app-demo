package com.org.appdemo.domain.usecase

import com.org.appdemo.common.LogUtil
import com.org.appdemo.domain.repository.ImageRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkAll
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test


class FetchImageUseCaseImplTest {

    private lateinit var useCase: FetchImageUseCase
    private lateinit var imageRepository: ImageRepository

    @Before
    fun setup() {
        mockkObject(LogUtil)
        every { LogUtil.debugLog(any(), any()) } returns Unit
        imageRepository = mockk()
        useCase = FetchImageUseCaseImpl(imageRepository)
    }

    @Test
    fun `verify remoteDataSource fetchImages`() = runTest {
        coEvery { imageRepository.getImages(any()) } returns mockk()
        runBlocking {
            useCase.invoke("")
        }
        coVerify(exactly = 1) { imageRepository.getImages(any()) }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

}