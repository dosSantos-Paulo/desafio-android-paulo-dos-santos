package com.dossantos.desafioandroid.data.characters

import com.dossantos.desafioandroid.data.utils.Constants.BASE_URL
import com.dossantos.desafioandroid.data.utils.Constants.DEFAULT_LIMIT
import com.dossantos.desafioandroid.data.utils.Constants.GET_CHARACTER_URL
import com.dossantos.desafioandroid.data.utils.Constants.MY_PUBLIC_KEY
import com.dossantos.desafioandroid.data.utils.Extensions.Companion.getHash
import com.dossantos.desafioandroid.data.utils.Extensions.Companion.getTimeStamp
import com.dossantos.desafioandroid.data.utils.HttpInterceptor
import com.dossantos.desafioandroid.model.character.CharacterDataWrapper

class CharacterRepository {

    private val _client = CharacterEndpoint.Endpoint


    suspend fun getCharacter(offset: Int): CharacterDataWrapper {
        HttpInterceptor.getInstance().setHttp(BASE_URL + GET_CHARACTER_URL + "?ts=" +
                getTimeStamp() + "&hash=" + getHash() + "&apikey="+ MY_PUBLIC_KEY + "&limit=" +
                DEFAULT_LIMIT + "&offset=" + offset)

        return _client.getCharacter(
            getTimeStamp(),
            getHash(),
            MY_PUBLIC_KEY,
            DEFAULT_LIMIT,
            offset
        )
    }

    suspend fun getCharacterByName(name:String, offset: Int): CharacterDataWrapper {
        HttpInterceptor.getInstance().setHttp(BASE_URL + GET_CHARACTER_URL + "?nameStartsWith=" + name
                + "&ts=" + getTimeStamp() + "&hash=" + getHash() + "&apikey="+ MY_PUBLIC_KEY + "&limit=" +
                DEFAULT_LIMIT + "&offset=" + offset)

        return _client.getCharacterByName(
            name,
            getTimeStamp(),
            getHash(),
            MY_PUBLIC_KEY,
            DEFAULT_LIMIT,
            offset
        )
    }

}