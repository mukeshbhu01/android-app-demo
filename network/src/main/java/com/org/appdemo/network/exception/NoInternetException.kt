package com.org.appdemo.network.exception

import java.io.IOException

class NoInternetException(override val message: String) : IOException()