package com.dossantos.desafioandroid.viewmodel.comic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.dossantos.desafioandroid.data.comic.ComicRepository
import com.dossantos.desafioandroid.model.comic.ComicModel
import kotlinx.coroutines.Dispatchers

class ComicViewModel(
    private val _repository: ComicRepository
) : ViewModel() {

    private var _comicsList: List<ComicModel> = listOf()
    private lateinit var _comic: ComicModel

    fun getAllComics() = liveData(Dispatchers.IO) {
        try{
            val response = _repository.getAllComics().data.results
            _comicsList = response
            emit(response)
        } catch (ex: Exception){
            println("ERRO: ${ex.message}")
        }

    }

    fun getComic(id: Int) = liveData(Dispatchers.IO) {
        val response = _repository.getComic(id).data.results[0]
        _comic = response
        emit(response)
    }

    class Factory (private val repository: ComicRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>) = ComicViewModel(repository) as T
    }
}