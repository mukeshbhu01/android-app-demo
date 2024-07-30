package com.org.appdemo.common

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

object Util {

    fun encodeUrl(url: String): String {
        return URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
    }
}