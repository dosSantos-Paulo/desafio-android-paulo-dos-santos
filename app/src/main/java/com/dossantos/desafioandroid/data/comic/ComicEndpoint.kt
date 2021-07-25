package com.dossantos.desafioandroid.data.comic

import com.dossantos.desafioandroid.data.utils.Constants.GET_COMIC_URL
import com.dossantos.desafioandroid.data.utils.NetworkUtils
import com.dossantos.desafioandroid.model.comic.ComicDataWrapModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ComicEndpoint {
    @GET(GET_COMIC_URL)
    suspend fun getAllComics(
        @Query("format") format: String?,
        @Query("formatType") formatType: String?,
        @Query("noVariants") noVariants: Boolean,
        @Query("ts") ts: String?,
        @Query("hash") hash: String?,
        @Query("apikey") apikey: String?
    ): ComicDataWrapModel

    @GET("$GET_COMIC_URL/{id}")
    suspend fun getComic(
        @Path("id") id:Int,
        @Query("format") format: String?,
        @Query("formatType") formatType: String?,
        @Query("noVariants") noVariants: Boolean,
        @Query("ts") ts: String?,
        @Query("hash") hash: String?,
        @Query("apikey") apikey: String?
    ): ComicDataWrapModel

    companion object {
        val endpoint: ComicEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(ComicEndpoint::class.java)
        }
    }
}