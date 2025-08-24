package com.agungkusuma.core.utils.network

import java.net.UnknownHostException

suspend fun <T> safeApiCall(networkUtils: NetworkUtils, call: suspend () -> T): T {
    if (!networkUtils.isNetworkAvailable()) throw UnknownHostException()
    return call()
}