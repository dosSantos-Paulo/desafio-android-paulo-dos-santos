package com.dossantos.desafioandroid.viewmodel.comic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.dossantos.desafioandroid.data.comic.ComicRepository
import com.dossantos.desafioandroid.data.utils.HttpInterceptor
import com.dossantos.desafioandroid.model.comic.ComicModel
import com.dossantos.desafioandroid.model.comic.ComicPriceModel
import com.dossantos.desafioandroid.model.comic.ComicSummary
import com.dossantos.desafioandroid.utils.StringUtils
import kotlinx.coroutines.Dispatchers

class ComicViewModel(
    private val _repository: ComicRepository
) : ViewModel() {

    private var _comicsList: MutableList<ComicModel> = mutableListOf()
    private var _comic: ComicModel? = null

    fun getComicById(id: Int) = liveData(Dispatchers.IO) {

        try {
            val response = _repository.getComicById(id)
            HttpInterceptor.getInstance().setStatusCode(response.code)
            HttpInterceptor.getInstance().setBody(response.data.toString())
            _comicsList.add(response.data.results[0])
            emit(response)
            HttpInterceptor.getInstance().printLog()
        } catch (ex: java.lang.Exception) {
            ex.printStackTrace()
        }

    }

    fun getMostValueComic(items: List<ComicSummary>) = liveData(Dispatchers.IO) {

        try {
            for (i in items.indices){
                val response = _repository.getComicById(StringUtils.getIdByUri(items[i].resourceURI))
                _comicsList.add(response.data.results[0])
            }
            emit(compare(_comicsList))
        } catch (ex: java.lang.Exception) {
            ex.printStackTrace()
        }

    }

    private fun compare(comicsList: MutableList<ComicModel>): List<ComicModel> {
        _comic = null
        val list: MutableList<ComicModel> = mutableListOf()
        var mostPrice: ComicPriceModel? = null
        comicsList.forEach {comicModel ->
            if (_comic == null) {
                comicModel.prices.forEach {comicPrice ->
                    if (mostPrice == null) {
                        mostPrice = comicPrice
                    } else {
                        if (mostPrice!!.price < comicPrice.price) {
                            mostPrice = comicPrice
                        }
                    }
                }
                _comic = comicModel
            } else {
                comicModel.prices.forEach {comicPrice ->
                    if (mostPrice == null) {
                        mostPrice = comicPrice
                        _comic = comicModel
                    } else {
                        if (mostPrice!!.price < comicPrice.price) {
                            mostPrice = comicPrice
                            _comic = comicModel
                        }
                    }
                }
            }
        }
        comicsList.forEach { comic ->
            comic.prices.forEach { price ->
                if (price.price == mostPrice!!.price){
                    list.add(comic)
                }
            }
        }

        return list
    }

    class Factory(private val repository: ComicRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>) = ComicViewModel(repository) as T
    }
}