package com.dossantos.desafioandroid.utils

class StringUtils {
    companion object{
        fun getIdByUri(uri: String): Int {
            val splited = uri.split("/")
            return splited[splited.lastIndex].toInt()
        }
    }

}