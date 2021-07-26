package com.dossantos.desafioandroid.data.characters

import com.dossantos.desafioandroid.data.utils.Constants.DEFAULT_LIMIT
import com.dossantos.desafioandroid.data.utils.Constants.GET_CHARACTER_URL
import com.dossantos.desafioandroid.data.utils.NetworkUtils
import com.dossantos.desafioandroid.model.character.CharacterDataWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterEndpoint {

    @GET(GET_CHARACTER_URL)
    suspend fun getCharacter(
        @Query("ts") ts: String,
        @Query("hash") hash: String,
        @Query("apikey") apikey: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): CharacterDataWrapper

    @GET(GET_CHARACTER_URL)
    suspend fun getCharacterByName(
        @Query("nameStartsWith") name: String,
        @Query("ts") ts: String,
        @Query("hash") hash: String,
        @Query("apikey") apikey: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): CharacterDataWrapper



    companion object {
        val Endpoint: CharacterEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(CharacterEndpoint::class.java)
        }
    }
}