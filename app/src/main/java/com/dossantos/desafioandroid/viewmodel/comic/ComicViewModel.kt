package com.dossantos.desafioandroid.viewmodel.comic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.dossantos.desafioandroid.data.comic.ComicRepository
import com.dossantos.desafioandroid.data.utils.HttpInterceptor
import com.dossantos.desafioandroid.model.comic.ComicModel
import kotlinx.coroutines.Dispatchers

class ComicViewModel(
    private val _repository: ComicRepository
) : ViewModel() {

    private var _comicsList: List<ComicModel> = listOf()
    private lateinit var _comic: ComicModel

    fun getAllComics() = liveData(Dispatchers.IO) {
        try{
            val response = _repository.getAllComics()
            HttpInterceptor.getInstance().setStatusCode(response.code)
            HttpInterceptor.getInstance().setBody(response.data.toString())
            _comicsList = response.data.results
            emit(response.data.results)
            HttpInterceptor.getInstance().printLog()
        } catch (ex: Exception){
            ex.printStackTrace()
        }

    }

    fun getComic(id: Int) = liveData(Dispatchers.IO) {

        try {
            val response = _repository.getComic(id)
            HttpInterceptor.getInstance().setStatusCode(response.code)
            HttpInterceptor.getInstance().setBody(response.data.toString())
            _comic = response.data.results[0]
            emit(response)
            HttpInterceptor.getInstance().printLog()
        }catch (ex: java.lang.Exception){
            ex.printStackTrace()
        }

    }

    class Factory (private val repository: ComicRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>) = ComicViewModel(repository) as T
    }
}