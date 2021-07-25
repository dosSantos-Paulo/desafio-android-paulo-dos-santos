package com.dossantos.desafioandroid.viewmodel.comic

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.dossantos.desafioandroid.CharacterResponseMock
import com.dossantos.desafioandroid.CoroutineTestViewModelRule
import com.dossantos.desafioandroid.data.comic.ComicRepository
import com.dossantos.desafioandroid.model.comic.ComicDataWrapModel
import com.dossantos.desafioandroid.model.comic.ComicModel
import com.dossantos.desafioandroid.model.comic.ComicSummary
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.only
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class ComicViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = CoroutineTestViewModelRule()

    @Mock
    private lateinit var repository: ComicRepository

    @Mock
    private lateinit var comicWrapperObserver: Observer<ComicDataWrapModel>

    @Mock
    private lateinit var comicObserver: Observer<List<ComicModel>>

    private lateinit var viewModel: ComicViewModel

    @Before
    fun setup(){
        viewModel = ComicViewModel(repository)
    }

    @Test
    fun getComicById() {
        testCoroutineRule.runBlockingTest {
            doReturn(CharacterResponseMock.getComicModel())
                .`when`(repository)
                .getComicById(1)
            viewModel.getComicById(1).observeForever(comicWrapperObserver)
            verify(repository, only()).getComicById(1)
            viewModel.getComicById(1).removeObserver(comicWrapperObserver)
        }
    }

    @Test
    fun getMostValueComic() {
        testCoroutineRule.runBlockingTest {
            doReturn(CharacterResponseMock.getComicModel())
                .`when`(repository)
                .getComicById(1)
            viewModel.getMostValueComic(listOf(ComicSummary("mock/1", "mock")))
                .observeForever(comicObserver)
            verify(repository, only()).getComicById(any())
            viewModel.getMostValueComic(listOf(ComicSummary("mock/1", "mock")))
                .removeObserver(comicObserver)
        }
    }
}