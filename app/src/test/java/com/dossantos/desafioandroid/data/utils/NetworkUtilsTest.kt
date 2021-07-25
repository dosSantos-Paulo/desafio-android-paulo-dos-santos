package com.dossantos.desafioandroid.data.utils

import com.dossantos.desafioandroid.data.utils.Constants.BASE_URL
import junit.framework.Assert.assertEquals
import junit.framework.Assert.fail
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception

@RunWith(MockitoJUnitRunner::class)
class NetworkUtilsTest {

    @Test
    fun getRetrofit(){
        try {
            assertEquals("$BASE_URL/", NetworkUtils.getRetrofitInstance().baseUrl().toString())
        }catch (ex: Exception){
            fail()
        }
    }
}