package com.dossantos.desafioandroid.data.utils

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import org.json.JSONObject

class HttpInterceptor {

    private val tag = "HttpInterceptor"
    private var http: String? = null
    private var statusCode: Int? = null
    private var body: String? = null

    fun setHttp(http: String) {
        this.http = http
    }

    fun setStatusCode(statusCode: Int) {
        this.statusCode = statusCode
    }

    fun setBody(body: String) {
        this.body = body
    }

    fun printLog() {
        if (http != null && statusCode != null && body != null) {
            Log.i(
                tag, "\n\n\n\n " +
                        "================ HTTP INTERCEPTOR ================ \n " +
                        "Request: $http \n Status Code: $statusCode \n Body: $body\n " +
                        "=================================================="
            )
        } else {
            throw NullPointerException("HttpInterceptor Error: interceptor does not work with null variable")
        }
        clear()
    }

    private fun clear() {
        http = null
        statusCode = null
        body = null
    }

    companion object {
        private var instance: HttpInterceptor? = null

        @JvmName("getInstance_httpInterceptor")
        fun getInstance(): HttpInterceptor {
            if (instance == null) {
                instance = HttpInterceptor()
            }
            return instance!!
        }
    }
}