package com.dossantos.desafioandroid.viewmodel.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.dossantos.desafioandroid.data.characters.CharacterRepository
import com.dossantos.desafioandroid.data.utils.HttpInterceptor
import com.dossantos.desafioandroid.model.character.CharacterDataWrapper
import com.dossantos.desafioandroid.model.character.CharacterModel
import kotlinx.coroutines.Dispatchers

class CharacterViewModel(private val _repository: CharacterRepository) : ViewModel() {

    private var offset: Int = 1
    private var nameOnSearch = ""

    fun getCharacter(pagePass: Boolean? = null) = liveData(Dispatchers.IO) {
        try {
            if (pagePass == true){
                offset++
            }else if (pagePass == false && offset > 1){
                offset--
            }
            val result = _repository.getCharacter(offset)
            HttpInterceptor.getInstance().setStatusCode(result.code)
            HttpInterceptor.getInstance().setBody(result.toString())
            emit(result.data.results)
            HttpInterceptor.getInstance().printLog()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    fun getCharacterByName(name: String?, pagePass: Boolean? = null) = liveData(Dispatchers.IO) {
        try {
            if (pagePass == true){
                offset++
            }else if (pagePass == false && offset > 1){
                offset--
            }
            val result: CharacterDataWrapper
            if (name == null && nameOnSearch.isEmpty()) {
                result = _repository.getCharacter(offset)
            } else {
                nameOnSearch = name!!
                result = _repository.getCharacterByName(nameOnSearch, offset)
            }
            HttpInterceptor.getInstance().setStatusCode(result.code)
            HttpInterceptor.getInstance().setBody(result.toString())
            emit(result.data.results)
            HttpInterceptor.getInstance().printLog()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    class Factory(private val repository: CharacterRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>) =
            CharacterViewModel(repository) as T
    }
}