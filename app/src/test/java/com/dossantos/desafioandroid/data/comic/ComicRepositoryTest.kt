package com.dossantos.desafioandroid.data.comic

import com.dossantos.desafioandroid.CoroutineTestRule
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ComicRepositoryTest {

    @get:Rule
    private var rule = CoroutineTestRule()
    private lateinit var repository: ComicRepository

    @Before
    fun setup() {
        repository = ComicRepository()
    }

    @Test
    fun getComicById() = runBlocking {
        val comic = repository.getComicById(40628)
        assertNotNull(comic)
        if (comic.code == 200){
            assertEquals("Hulk (2008) #55", comic.data.results[0].title)
        }
    }
}