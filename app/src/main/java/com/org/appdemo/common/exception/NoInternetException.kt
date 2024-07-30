package com.org.appdemo.common.exception

import okio.IOException

class NoInternetException(override val message: String) : IOException()