package com.tang.alex.wanandroid.utils.network

import android.util.Log

import java.io.IOException

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class LoggingInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Log.e(TAG, "url:" + request.url())
        val startTime = System.nanoTime()
        Log.d(TAG, String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()))

        val response = chain.proceed(request)

        val endTime = System.nanoTime()
        Log.d(TAG, String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (endTime - startTime) / 1e6, response.headers()))

        return response
    }

    companion object {
        private val TAG = "LoggingInterceptor"
    }
}
