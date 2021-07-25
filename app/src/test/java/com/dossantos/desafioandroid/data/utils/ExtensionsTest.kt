package com.dossantos.desafioandroid.data.utils

import junit.framework.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class ExtensionsTest {

    @Test
    fun `test timeStamp()`(){
        assertEquals((Calendar.getInstance().timeInMillis / 1000).toString(), Extensions.getTimeStamp())
    }

    @Test
    fun `test getHasg()`(){
        assertFalse(Extensions.getHash().isNullOrBlank())
    }
}