package com.dossantos.desafioandroid.viewmodel.character

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.dossantos.desafioandroid.CharacterResponseMock
import com.dossantos.desafioandroid.CoroutineTestViewModelRule
import com.dossantos.desafioandroid.data.characters.CharacterRepository
import com.dossantos.desafioandroid.model.character.CharacterModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.only
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class CharacterViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = CoroutineTestViewModelRule()

    @Mock
    private lateinit var repository: CharacterRepository

    @Mock
    private lateinit var characterObserver: Observer<MutableList<CharacterModel>>

    private lateinit var viewModel: CharacterViewModel

    @Before
    fun setup(){
        viewModel = CharacterViewModel(repository)
    }

    @Test
    fun getCharacter() {
        testCoroutineRule.runBlockingTest {
            doReturn(CharacterResponseMock.getCharacterModel())
                .`when`(repository)
                .getCharacter(1)
            viewModel.getCharacter().observeForever(characterObserver)
            verify(repository, only()).getCharacter(1)
            viewModel.getCharacter().removeObserver(characterObserver)
        }
    }



}