package com.dossantos.desafioandroid.data.utils

import com.dossantos.desafioandroid.data.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkUtils {

    companion object {
        fun getRetrofitInstance(baseUrl: String = BASE_URL): Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}