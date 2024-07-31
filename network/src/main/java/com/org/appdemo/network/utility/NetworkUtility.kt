package com.org.appdemo.network.utility

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtility {

    fun isInternet(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetwork != null
    }
}