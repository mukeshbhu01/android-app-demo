package com.org.appdemo.common

import android.content.Context
import android.widget.Toast
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

object Util {

    fun encodeUrl(url: String): String {
        return URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
    }

    fun showToastMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}