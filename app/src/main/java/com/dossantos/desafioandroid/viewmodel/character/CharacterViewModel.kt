package com.dossantos.desafioandroid.viewmodel.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.dossantos.desafioandroid.data.characters.CharacterRepository
import com.dossantos.desafioandroid.model.character.CharacterModel
import kotlinx.coroutines.Dispatchers

class CharacterViewModel( private val _repository: CharacterRepository ) : ViewModel() {

    private var characterList: MutableList<CharacterModel> = mutableListOf()
    private var offset: Int = 1

    fun getCharacter() = liveData(Dispatchers.IO) {
        try {
            val result = _repository.getCharacter(offset).data.results
            characterList.addAll(result)
            emit(characterList)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    class Factory (private val repository: CharacterRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>) =
            CharacterViewModel(repository) as T
    }
}