package com.org.appdemo.data.repository

import com.org.appdemo.common.LogUtil
import com.org.appdemo.data.source.remote.RemoteDataSource
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


class ImageRepositoryImplTest {

    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var imageRepository: ImageRepository

    @Before
    fun setup() {
        mockkObject(LogUtil)
        every { LogUtil.debugLog(any(), any()) } returns Unit
        remoteDataSource = mockk()
        imageRepository = ImageRepositoryImpl(remoteDataSource = remoteDataSource)
    }

    @Test
    fun `verify remoteDataSource fetchImages`() = runTest {
        coEvery { remoteDataSource.fetchImages(any()) } returns mockk()
        runBlocking {
            imageRepository.getImages("")
        }
        coVerify(exactly = 1) { remoteDataSource.fetchImages(any()) }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

}