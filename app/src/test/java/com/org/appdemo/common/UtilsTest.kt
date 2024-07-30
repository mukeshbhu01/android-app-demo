package com.org.appdemo.common

import android.util.Log
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class UtilsTest {

    @Before
    fun setup() {
       mockkStatic(Log::class)
    }

    @Test
    fun `verify Log d`() = runTest {
        every { Log.d(any(), any()) } returns 0

        LogUtil.debugLog("", "")
        verify(exactly = 1) { Log.d(any(), any()) }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}