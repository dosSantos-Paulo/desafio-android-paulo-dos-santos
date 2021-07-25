package com.dossantos.desafioandroid.data.comic

import com.dossantos.desafioandroid.data.utils.Constants.MY_PUBLIC_KEY
import com.dossantos.desafioandroid.data.utils.Extensions.Companion.getHash
import com.dossantos.desafioandroid.data.utils.Extensions.Companion.getTimeStamp


class ComicRepository {
    private val _client = ComicEndpoint.endpoint

    suspend fun getAllComics() = _client.getAllComics(
        "comic",
        "comic",
        true,
        getTimeStamp(),
        getHash(),
        MY_PUBLIC_KEY
    )

    suspend fun getComic(id: Int) = _client.getComic(
        id,
        "comic",
        "comic",
        true,
        getTimeStamp(),
        getHash(),
        MY_PUBLIC_KEY
    )
}