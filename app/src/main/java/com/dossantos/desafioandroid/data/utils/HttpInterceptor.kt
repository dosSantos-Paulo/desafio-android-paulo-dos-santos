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

    fun setHttp(http: String): HttpInterceptor {
        this.http = http
        return this
    }

    fun setStatusCode(statusCode: Int): HttpInterceptor {
        this.statusCode = statusCode
        return this
    }

    fun setBody(body: String): HttpInterceptor {
        this.body = body
        return this
    }

    fun printLog() {
        Log.i(
            tag, "\n\n\n\n " +
                    "================ HTTP INTERCEPTOR ================ \n " +
                    "Request: $http \n Status Code: $statusCode \n Body: $body\n " +
                    "=================================================="
        )
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