package com.org.appdemo.data.repository

import android.util.Log
import com.org.appdemo.data.source.remote.RemoteDataSource
import com.org.appdemo.domain.repository.HomeImageRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test


class HomeImageRepositoryImplTest {

    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var homeImageRepository: HomeImageRepository

    @Before
    fun setup() {
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
        remoteDataSource = mockk()
        homeImageRepository = HomeImageRepositoryImpl(remoteDataSource = remoteDataSource)
    }

    @Test
    fun `verify remoteDataSource fetchImages`() = runTest {
        coEvery { remoteDataSource.fetchImages(any()) } returns mockk()
        runBlocking {
            homeImageRepository.getImages("")
        }
        coVerify(exactly = 1) { remoteDataSource.fetchImages(any()) }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

}