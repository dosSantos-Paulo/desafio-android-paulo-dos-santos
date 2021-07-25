package com.dossantos.desafioandroid.data.characters

import com.dossantos.desafioandroid.data.utils.Constants.DEFAULT_LIMIT
import com.dossantos.desafioandroid.data.utils.Constants.MY_PUBLIC_KEY
import com.dossantos.desafioandroid.data.utils.Extensions.Companion.getHash
import com.dossantos.desafioandroid.data.utils.Extensions.Companion.getTimeStamp

class CharacterRepository {

    private val _client = CharacterEndpoint.Endpoint

    suspend fun getCharacter(offset: Int) = _client.getCharacter(
        getTimeStamp(),
        getHash(),
        MY_PUBLIC_KEY,
        DEFAULT_LIMIT,
        offset
    )
}