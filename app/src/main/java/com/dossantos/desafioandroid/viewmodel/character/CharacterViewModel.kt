package com.dossantos.desafioandroid.viewmodel.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.dossantos.desafioandroid.data.characters.CharacterRepository
import com.dossantos.desafioandroid.data.utils.HttpInterceptor
import com.dossantos.desafioandroid.model.character.CharacterModel
import kotlinx.coroutines.Dispatchers

class CharacterViewModel( private val _repository: CharacterRepository ) : ViewModel() {

    private var characterList: MutableList<CharacterModel> = mutableListOf()
    private var offset: Int = 1

    fun getCharacter() = liveData(Dispatchers.IO) {
        try {
            val result = _repository.getCharacter(offset)
            HttpInterceptor.getInstance().setStatusCode(result.code)
            HttpInterceptor.getInstance().setBody(result.toString())
            characterList.addAll(result.data.results)
            emit(characterList)
            HttpInterceptor.getInstance().printLog()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    fun getList() = characterList

    class Factory (private val repository: CharacterRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>) =
            CharacterViewModel(repository) as T
    }
}