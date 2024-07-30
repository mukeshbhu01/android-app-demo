package com.org.appdemo.common

import android.util.Log

object LogUtil {

    fun debugLog(tag: String = "Demo", log: String) {
        Log.d(tag, log)
    }
}