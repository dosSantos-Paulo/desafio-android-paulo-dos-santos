package com.dossantos.desafioandroid.data.utils

import junit.framework.Assert.assertEquals
import junit.framework.Assert.fail
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.lang.NullPointerException
import java.lang.RuntimeException

@RunWith(MockitoJUnitRunner::class)
class HttpInterceptorTest {

    @Test
    fun createInstance() {
        try{
            HttpInterceptor.getInstance()
                .setHttp("http")
                .setStatusCode(200)
                .setBody("body")
                .printLog()

        }catch (ex: NullPointerException){
            fail()
        }catch (ex: RuntimeException){
            //Ignore - log function exception
        }
    }

    @Test
    fun createInstanceNullPointerException() {
        try{
            HttpInterceptor.getInstance()
                .printLog()
            fail()
        }catch (ex: NullPointerException){
            assertEquals("HttpInterceptor Error: interceptor does not work with null variable", ex.message)
        }
    }

}