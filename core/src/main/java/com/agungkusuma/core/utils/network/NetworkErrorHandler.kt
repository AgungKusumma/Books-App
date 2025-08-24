package com.agungkusuma.core.utils.network

import android.content.Context
import com.agungkusuma.common.R
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object NetworkErrorHandler {

    fun getErrorMessage(context: Context, e: Throwable): String {
        return when (e) {
            is SocketTimeoutException ->
                context.getString(R.string.error_timeout)

            is UnknownHostException ->
                context.getString(R.string.error_host)

            is IOException ->
                context.getString(R.string.error_io)

            is HttpException -> {
                when (e.code()) {
                    400 -> context.getString(R.string.error_bad_request)
                    401 -> context.getString(R.string.error_unauthorized)
                    403 -> context.getString(R.string.error_forbidden)
                    404 -> context.getString(R.string.error_not_found)
                    500 -> context.getString(R.string.error_server)
                    in 500..599 -> context.getString(R.string.error_server) // server lain-lain
                    else -> context.getString(
                        R.string.error_unknown_http,
                        e.code()
                    )
                }
            }

            else ->
                context.getString(R.string.error_unknown)
        }
    }
}