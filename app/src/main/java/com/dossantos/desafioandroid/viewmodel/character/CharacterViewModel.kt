package com.dossantos.desafioandroid.viewmodel.character

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.dossantos.desafioandroid.data.characters.CharacterRepository
import com.dossantos.desafioandroid.data.utils.Constants.DEFAULT_LIMIT
import com.dossantos.desafioandroid.data.utils.HttpInterceptor
import com.dossantos.desafioandroid.model.character.CharacterDataWrapper
import com.dossantos.desafioandroid.model.character.CharacterModel
import kotlinx.coroutines.Dispatchers

class CharacterViewModel(private val _repository: CharacterRepository) : ViewModel() {

    private var offset: Int = 0
    private var page: Int = 1
    private var nameOnSearch = ""
    private var previousList: List<CharacterModel> = listOf()

    fun getCharacters(name: String?, isNewSearch: Boolean? = null) = liveData(Dispatchers.IO) {
        try {
            if (isNewSearch == true){
                nameOnSearch = ""
            }
            if (name != null){
                nameOnSearch = name
            }
            offset = 0
            page = 1
            val result: CharacterDataWrapper
            if (nameOnSearch.trim().isEmpty()) {
                result = _repository.getCharacter(offset)
            } else {
                result = _repository.getCharacterByName(nameOnSearch, offset)
            }
            HttpInterceptor.getInstance().setStatusCode(result.code)
            HttpInterceptor.getInstance().setBody(result.toString())
            previousList = result.data.results
            emit(result.data.results)
            HttpInterceptor.getInstance().printLog()
        } catch (ex: Exception) {
            Log.d("CharacterViewmModel", "getCharacters() Error: ${ex.message}")
            ex.printStackTrace()
            emit(null)
        }
    }

    fun getNextPage() = liveData(Dispatchers.IO) {
        try {
            if (previousList.size <= DEFAULT_LIMIT){
                offset += DEFAULT_LIMIT
                page++
                val result: CharacterDataWrapper
                if (nameOnSearch.trim().isEmpty()){
                    result = _repository.getCharacter(offset)
                }else {
                    result = _repository.getCharacterByName(nameOnSearch, offset)
                }
                HttpInterceptor.getInstance().setStatusCode(result.code)
                HttpInterceptor.getInstance().setBody(result.toString())
                previousList = result.data.results
                emit(result.data.results)
                HttpInterceptor.getInstance().printLog()
            }else {
                emit(null)
            }
        }catch (ex: Exception){
            Log.d("CharacterViewmModel", "getNextPage() Error: ${ex.message}")
            ex.printStackTrace()
            emit(null)
        }
    }

    fun getPreviousPage() = liveData(Dispatchers.IO) {
        try {
            if (page > 1){
                offset -= DEFAULT_LIMIT
                page--
                val result: CharacterDataWrapper
                if (nameOnSearch.trim().isEmpty()){
                    result = _repository.getCharacter(offset)
                }else {
                    result = _repository.getCharacterByName(nameOnSearch, offset)
                }
                HttpInterceptor.getInstance().setStatusCode(result.code)
                HttpInterceptor.getInstance().setBody(result.toString())
                previousList = result.data.results
                emit(result.data.results)
                HttpInterceptor.getInstance().printLog()
            }else {
                emit(null)
            }
        }catch (ex: Exception){
            Log.d("CharacterViewmModel", "getNextPage() Error: ${ex.message}")
            ex.printStackTrace()
            emit(null)
        }
    }



    class Factory(private val repository: CharacterRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>) =
            CharacterViewModel(repository) as T
    }
}