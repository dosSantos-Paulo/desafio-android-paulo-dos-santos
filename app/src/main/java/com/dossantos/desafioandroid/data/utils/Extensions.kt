package com.dossantos.desafioandroid.data.utils

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class Extensions {
    companion object {
        private val hexChar = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')
        private fun hexEncode(bytes: ByteArray): String {
            val result = CharArray(bytes.size * 2)
            var b: Int
            var i = 0
            var j = 0
            while (i < bytes.size) {
                b = bytes[i].toInt() and 0xff
                result[j++] = hexChar[b shr 4]
                result[j++] = hexChar[b and 0xf]
                i++
            }
            return String(result)
        }
        private fun md5HashGenerator(string: String): String {
            try {
                val digest = MessageDigest.getInstance("MD5")
                digest.update(string.toByteArray())
                return hexEncode(digest.digest())
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            }
            return ""
        }
        fun getTimeStamp() = (Calendar.getInstance().timeInMillis / 1000).toString()
        fun getHash() = md5HashGenerator(getTimeStamp() + Constants.MY_PRIVATE_KEY + Constants.MY_PUBLIC_KEY)
    }
}