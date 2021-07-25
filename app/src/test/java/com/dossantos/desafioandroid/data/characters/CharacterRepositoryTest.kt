package com.dossantos.desafioandroid.data.characters

import com.dossantos.desafioandroid.data.CoroutineTestRule
import com.dossantos.desafioandroid.data.utils.Constants.DEFAULT_LIMIT
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharacterRepositoryTest {

    @get:Rule
    private var coroutinesTestRule = CoroutineTestRule()
    private lateinit var repository: CharacterRepository

    @Before
    fun setup() {
        repository = CharacterRepository()
    }

    @Test
    fun getCharacter() = runBlocking {
        val list = repository.getCharacter(1)
        assertNotNull(list)
        if (list.code == 200){
            assertEquals(DEFAULT_LIMIT, list.data.results.size)
        }
    }

}