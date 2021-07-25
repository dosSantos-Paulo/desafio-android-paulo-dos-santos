package com.dossantos.desafioandroid.data.comic

import com.dossantos.desafioandroid.data.utils.Constants.BASE_URL
import com.dossantos.desafioandroid.data.utils.Constants.GET_COMIC_URL
import com.dossantos.desafioandroid.data.utils.Constants.MY_PUBLIC_KEY
import com.dossantos.desafioandroid.data.utils.Extensions.Companion.getHash
import com.dossantos.desafioandroid.data.utils.Extensions.Companion.getTimeStamp
import com.dossantos.desafioandroid.data.utils.HttpInterceptor
import com.dossantos.desafioandroid.model.comic.ComicDataWrapModel


class ComicRepository {
    private val _client = ComicEndpoint.endpoint

    suspend fun getComicById(id: Int): ComicDataWrapModel {
        HttpInterceptor.getInstance().setHttp(BASE_URL + GET_COMIC_URL + "/" + id + "?format=comic&" +
                "formatType=comic&noVariants=true&ts=" + getTimeStamp() + "&hash=" + getHash() +
                "&apikey=" + MY_PUBLIC_KEY)

        return _client.getComicById(
            id,
            "comic",
            "comic",
            true,
            getTimeStamp(),
            getHash(),
            MY_PUBLIC_KEY
        )
    }
}